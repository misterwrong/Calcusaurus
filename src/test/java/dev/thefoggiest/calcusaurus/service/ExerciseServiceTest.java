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

import dev.thefoggiest.calcusaurus.service.ExerciseService;
import static dev.thefoggiest.calcusaurus.service.ExerciseService.MaxNumber;
import java.util.Arrays;
import java.util.List;
import dev.thefoggiest.calcusaurus.model.Assignment;
import dev.thefoggiest.calcusaurus.model.SimpleAdditionAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleDivisionAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleMultiplicationAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleSubstractionAssignment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class ExerciseServiceTest
{
    @org.junit.jupiter.api.Test
    public void testGet1Addition()
    {
        List<String> types = Arrays.asList(SimpleAdditionAssignment.TYPE);
        ExerciseService service = new ExerciseService(types, 1, MaxNumber.TEN, false, false);

        Assignment assignment = service.getNext();
        assertTrue(assignment instanceof SimpleAssignment);
        assertEquals(assignment.getSolution(),
            assignment.getFirstNumber() + assignment.getSecondNumber());

        Assignment nothing = service.getNext();
        assertNull(nothing);
    }

    @org.junit.jupiter.api.Test
    public void testGet1AdditionWithMinIsZero()
    {
        List<String> types = Arrays.asList(SimpleAdditionAssignment.TYPE);
        ExerciseService service = new ExerciseService(types, 1, MaxNumber.TEN, true, false);

        Assignment assignment = service.getNext();
        assertTrue(assignment instanceof SimpleAssignment);
        assertEquals(assignment.getSolution(),
            assignment.getFirstNumber() + assignment.getSecondNumber());

        Assignment nothing = service.getNext();
        assertNull(nothing);
    }

    @org.junit.jupiter.api.Test
    public void testGet4Additions()
    {
        List<String> types = Arrays.asList(SimpleAdditionAssignment.TYPE);
        ExerciseService service = new ExerciseService(types, 4, MaxNumber.TEN, false, false);

        for (int i = 0; i < 4; i++)
        {
            Assignment assignment = service.getNext();
            assertTrue(assignment instanceof SimpleAdditionAssignment);
            assertEquals(assignment.getSolution(),
                assignment.getFirstNumber() + assignment.getSecondNumber());
        }

        Assignment nothing = service.getNext();
        assertNull(nothing);
    }

    @org.junit.jupiter.api.Test
    public void testGet4Multiplications()
    {
        List<String> types = Arrays.asList(SimpleMultiplicationAssignment.TYPE);
        ExerciseService service = new ExerciseService(types, 4, MaxNumber.TEN, false, false);

        for (int i = 0; i < 4; i++)
        {
            Assignment assignment = service.getNext();
            assertTrue(assignment instanceof SimpleMultiplicationAssignment);
            assertEquals(assignment.getSolution(),
                assignment.getFirstNumber() * assignment.getSecondNumber());
        }

        Assignment nothing = service.getNext();
        assertNull(nothing);
    }

    @org.junit.jupiter.api.Test

    public void testGet4Divisions()
    {
        List<String> types = Arrays.asList(SimpleDivisionAssignment.TYPE);
        ExerciseService service = new ExerciseService(types, 4, MaxNumber.TEN, false, false);

        for (int i = 0; i < 4; i++)
        {
            Assignment assignment = service.getNext();
            assertTrue(assignment instanceof SimpleDivisionAssignment);
            assertEquals(assignment.getSolution(),
                assignment.getFirstNumber() / assignment.getSecondNumber());
        }

        Assignment nothing = service.getNext();
        assertNull(nothing);
    }

    @org.junit.jupiter.api.Test
    public void testGet4AdditionsAndSubstractions()
    {
        List<String> types = Arrays.asList(SimpleAdditionAssignment.TYPE,
            SimpleSubstractionAssignment.TYPE);

        ExerciseService service = new ExerciseService(types, 4, MaxNumber.TEN, false, false);

        for (int i = 0; i < 4; i++)
        {
            Assignment assignment = service.getNext();
            assertTrue(assignment instanceof SimpleAssignment);

            if (assignment.getType().equals(SimpleAdditionAssignment.TYPE))
            {
                assertEquals(assignment.getSolution(),
                    assignment.getFirstNumber() + assignment.getSecondNumber());
            }
            if (assignment.getType().equals(SimpleSubstractionAssignment.TYPE))
            {
                assertEquals(assignment.getSolution(),
                    assignment.getFirstNumber() - assignment.getSecondNumber());
            }
        }

        Assignment nothing = service.getNext();
        assertNull(nothing);
    }
}
