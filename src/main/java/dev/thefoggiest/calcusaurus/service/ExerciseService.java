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
package dev.thefoggiest.calcusaurus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import dev.thefoggiest.calcusaurus.model.Assignment;
import dev.thefoggiest.calcusaurus.model.SimpleAdditionAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleDivisionAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleMultiplicationAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleSubstractionAssignment;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class ExerciseService
{
    private final List<Assignment> assignments;
    private int current;

    public enum MaxNumber
    {
        TEN(10, "Tien"),
        TWENTY(20, "Twintig"),
        HUNDRED(100, "Honderd"),
        THOUSAND(1000, "Duizend"),
        ANY(Integer.MAX_VALUE, "Oneindig");

        int max;
        String label;

        MaxNumber(int max, String label)
        {
            this.max = max;
            this.label = label;
        }
        
        @Override
        public String toString() {
            return this.label;
        }
    }

    private static final int MIN_DIV = 1;

    /**
     * Creates an exercise with n assignments of the types given.
     *
     * TODO: Create non random of assignments of each wanted type and then use Collections.shuffle
     *
     * @param types The types of assigments to be included in the exercise
     * @param n The amount of assigments in the exercise
     * @param highest The highest number as 0 (10), 1 (100), 2 (1000), 3 (Infinite)
     * @param zero Whether to include zero as a number
     * @param negative Whether to include negative numbers. In that case, the lowest number will be
     * the inverse of the highest number (0 - max)
     *
     */
    public ExerciseService(final List<String> types, int n, MaxNumber highest, boolean zero, boolean negative)
    {
        if (negative && !zero)
        {
            throw new IllegalArgumentException("Cannot use negative numbers without zero");
        }

        int max = highest.max;

        int min;
        if (negative)
        {
            min = 0 - max;
        }
        else
        {
            min = zero ? 0 : 1;
        }

        assignments = new ArrayList<>();
        Random random = new Random();

        var additionService = new AdditionService();
        var substractionService = new SubstractionService();
        var multiplicationService = new MultiplicationService();
        var divisionService = new DivisionService();

        for (int j = 0; j < n; j++)
        {
            Assignment assignment = null;

            String type = types.get(random.nextInt(types.size()));
            if (type.equals(SimpleAdditionAssignment.TYPE))
            {
                assignment = additionService.createAssignment(min, max);
            }
            else if (type.equals(SimpleSubstractionAssignment.TYPE))
            {
                assignment = substractionService.createAssignment(min, max);
            }
            else if (type.equals(SimpleMultiplicationAssignment.TYPE))
            {
                assignment = multiplicationService.createAssignment(min, max);
            }
            else if (type.equals(SimpleDivisionAssignment.TYPE))
            {
                assignment = divisionService.createAssignment(MIN_DIV, max);
            }

            assignments.add(assignment);
        }

        current = 0;
    }

    /**
     * Returns the next assignment
     *
     * @return
     */
    public Assignment getNext()
    {
        if (current == assignments.size())
        {
            return null;
        }
        return assignments.get(current++);
    }
}
