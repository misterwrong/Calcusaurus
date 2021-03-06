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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class DinoPanel extends JPanel
{
    private BufferedImage image;
    private BubblePanel bubblePanel;

    /**
     * Creates new form DinoPanel
     */
    public DinoPanel()
    {
        initComponents();
        try
        {
            image = ImageIO.read(getClass().getResource("/dev/thefoggiest/calcusaurus/view/dinos.jpg"));
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void addBubble(int x, int y, BubblePanel bubblePanel)
    {
        this.bubblePanel = bubblePanel;
        this.add(bubblePanel);
        Insets insets = this.getInsets();
        Dimension size = bubblePanel.getPreferredSize();
        bubblePanel.setBounds(x + insets.left, y + insets.top, size.width, size.height);
        revalidate();
    }

    public void removeBubble()
    {
        this.remove(bubblePanel);
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
