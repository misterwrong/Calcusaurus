/*
 * Copyright (C) 2019 Mister Wrong <hello@thefoggiest.dev>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package dev.thefoggiest.calcusaurus.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import dev.thefoggiest.calcusaurus.model.Assignment;
import dev.thefoggiest.calcusaurus.service.ExerciseService;
import dev.thefoggiest.calcusaurus.util.NumberUtil;
import java.util.ResourceBundle;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public final class CalcusaurusFrame extends javax.swing.JFrame
{
    private static final ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
    
    private int score;

    private final DinoPanel dinoPanel;
    private SelectionPanel selectionPanel;
    private final List<String> selection;

    private Semaphore exerciseSemaphore;
    private Semaphore appSemaphore;
    private final ExecutorService threadPool;

    /**
     * Creates new form RekenenFrame
     */
    public CalcusaurusFrame()
    {
        initComponents();

        selection = new ArrayList<>();
        dinoPanel = new DinoPanel();
        this.setLocationByPlatform(true);
        this.add(dinoPanel, BorderLayout.CENTER);
        this.setSize(1280, 720);
        
        threadPool = Executors.newFixedThreadPool(1);

        appSemaphore = new Semaphore(1);
        
        threadPool.execute(() -> {
            while (true) {
                try
                {
                    appSemaphore.acquire();
                    go();
                } catch (InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    private void go()
    {
        ActionListener menuListener = (ActionEvent ae) ->
        {
            selectionPanel.getSelection()
                .stream()
                .filter((checkbox) -> (checkbox.isSelected()))
                .forEachOrdered((checkbox) ->
                {
                    selection.add(checkbox.getName());
                });
            dinoPanel.removeBubble();
            startExercises(selection);
        };

        selectionPanel = new SelectionPanel(menuListener);
        BubblePanel mainMenu = new BubblePanel(BubblePanel.BUBBLE_IMAGE_SE, selectionPanel);
        dinoPanel.addBubble(300, 250, mainMenu);
    }

    private void startExercises(List<String> selection)
    {
        var exerciseTypePanel = new ExerciseTypePanel((var ae) ->
        {
            dinoPanel.removeBubble();
            JButton button = (JButton)ae.getSource();
            ExerciseTypePanel panel = (ExerciseTypePanel)button.getParent();
            int numberOfAssignments = panel.getAmount();
            
            ExerciseService exerciseService = new ExerciseService(selection, numberOfAssignments, 
                panel.getHighestNumber(), panel.doZero(), panel.doNegative());
            
            exerciseSemaphore = new Semaphore(0);
            Semaphore scoreSemaphore = new Semaphore(1);
            ExecutorService exercisePool = Executors.newFixedThreadPool(1);

            score = 0;

            try
            {
                scoreSemaphore.acquire();

                for (int i = 0; i < numberOfAssignments; i++)
                {
                    exercisePool.execute(() ->
                    {
                        try
                        {
                            Assignment assignment = exerciseService.getNext();
                            doAssignment(assignment);
                            exerciseSemaphore.acquire();
                        } catch (InterruptedException ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                    });
                }

                scoreSemaphore.release();

                exercisePool.execute(() ->
                {
                    showFinalScore(numberOfAssignments, score);
                });

            } catch (InterruptedException ex)
            {
                Logger.getLogger(CalcusaurusFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        BubblePanel textBubble = new BubblePanel(BubblePanel.BUBBLE_IMAGE_NW, exerciseTypePanel);
        dinoPanel.addBubble(160, 100, textBubble);
    }

    private void doAssignment(Assignment assignment)
    {
        var assignmentPanel = new AssignmentPanel(assignment, (var ae) ->
        {
            var textField = (JTextField) ae.getSource();
            Integer solution = null;
            try {
                solution = NumberUtil.getInt(textField.getText());
            } catch (IllegalArgumentException e) {
                textField.setText("");
            }
            if (null != solution)
            {
                dinoPanel.removeBubble();
                if (solution == assignment.getSolution())
                {
                    score++;
                    showAnswerCorrect();
                }
                else
                {
                    showAnswerWrong(assignment);
                }
            }
        });
        BubblePanel assignmentBubble = new BubblePanel(BubblePanel.BUBBLE_IMAGE_NE, assignmentPanel);
        dinoPanel.addBubble(500, 200, assignmentBubble);
    }
    
    private void showFinalScore(int numberOfAssignments, int score)
    {
        String text;
        if (score == numberOfAssignments) {
            text = bundle.getString("SUPER! JE HAD ALLES GOED!");
        }
        else {
            text = java.text.MessageFormat.format(bundle
                    .getString("KLAAR! JE HAD {0} VAN DE {1} GOED!"), new Object[] {score, numberOfAssignments});
        }
        var scorePanel = new ScorePanel(text,
            (var again) ->
        {
            dinoPanel.removeBubble();
            exerciseSemaphore.release();
            appSemaphore.release();
        },
            (var stop) ->
        {
            System.exit(0);
        });
        BubblePanel correctBubble = new BubblePanel(BubblePanel.BUBBLE_IMAGE_NW, scorePanel);
        dinoPanel.addBubble(160, 100, correctBubble);
    }

    private void showAnswerCorrect()
    {
        // TODO: Vary congratulations
        var correctPanel = new TextPanel_SW(bundle.getString("HELEMAAL GOED!"), (var e) ->
        {
            dinoPanel.removeBubble();
            exerciseSemaphore.release();
        });
        BubblePanel correctBubble = new BubblePanel(BubblePanel.BUBBLE_IMAGE_SW, correctPanel);
        dinoPanel.addBubble(50, 300, correctBubble);
    }

    private void showAnswerWrong(final Assignment assignment)
    {
        var correctPanel = new TextPanel_NW(java.text.MessageFormat.format(bundle
                .getString("NEE, HELAAS: {0}"), new Object[] {assignment}), (var e) ->
        {
            dinoPanel.removeBubble();
            exerciseSemaphore.release();
        });
        BubblePanel correctBubble = new BubblePanel(BubblePanel.BUBBLE_IMAGE_NW, correctPanel);
        dinoPanel.addBubble(160, 100, correctBubble);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("CALCUSAURUS")); // NOI18N
        setBackground(getBackground());
        setResizable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
