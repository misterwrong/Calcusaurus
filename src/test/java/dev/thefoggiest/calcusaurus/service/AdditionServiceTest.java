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

import dev.thefoggiest.calcusaurus.service.AdditionService;
import dev.thefoggiest.calcusaurus.model.SimpleAssignment;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class AdditionServiceTest
{
    /**
     * Test of createAssignment method, of class AdditionService.
     */
    @org.junit.jupiter.api.Test
    public void testCreateAssignmentPositive()
    {
        int min = 1;
        int max = 10;

        AdditionService instance = new AdditionService();

        for (int i = 0; i < 10; i++)
        {
            SimpleAssignment assignment = instance.createAssignment(min, max);
            assertTrue(assignment.getFirstNumber() >= min && assignment.getFirstNumber() <= max);
            assertTrue(assignment.getSecondNumber() >= min && assignment.getSecondNumber() <= max);
            assertTrue(assignment.getSolution() >= min && assignment.getSolution() <= max);
            assertEquals(assignment.getSolution(), assignment.getFirstNumber() + assignment.getSecondNumber());
        }
    }

    @org.junit.jupiter.api.Test
    public void testCreateAssignmentZero()
    {
        int min = 0;
        int max = 10;

        AdditionService instance = new AdditionService();

        for (int i = 0; i < 10; i++)
        {
            SimpleAssignment assignment = instance.createAssignment(min, max);
            assertTrue(assignment.getFirstNumber() >= min && assignment.getFirstNumber() <= max);
            assertTrue(assignment.getSecondNumber() >= min && assignment.getSecondNumber() <= max);
            assertTrue(assignment.getSolution() >= min && assignment.getSolution() <= max);
            assertEquals(assignment.getSolution(), assignment.getFirstNumber() + assignment.getSecondNumber());
        }
    }
}
