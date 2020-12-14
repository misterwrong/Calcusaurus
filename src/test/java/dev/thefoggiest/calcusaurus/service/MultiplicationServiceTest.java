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

import dev.thefoggiest.calcusaurus.service.MultiplicationService;
import dev.thefoggiest.calcusaurus.model.SimpleAssignment;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class MultiplicationServiceTest
{
    /**
     * Test of createAssignment method, of class MultiplicationService.
     */
    @org.junit.jupiter.api.Test
    public void testCreatePositiveAssignment()
    {
        int min = 1;
        int max = 25;
        MultiplicationService instance = new MultiplicationService();
        
        for (int i = 0; i < 10; ++i) 
        {
            SimpleAssignment result = instance.createAssignment(min, max);
            assertTrue(result.getFirstNumber() >= min && result.getFirstNumber() <= max);
            assertTrue(result.getSecondNumber() >= min && result.getSecondNumber() <= max);
            assertEquals(result.getSolution(), result.getFirstNumber() * result.getSecondNumber());
        }
    }
    
    @org.junit.jupiter.api.Test
    public void testCreateNullableAssignment()
    {
        int min = 0;
        int max = 25;
        MultiplicationService instance = new MultiplicationService();
        
        for (int i = 0; i < 10; ++i) 
        {
            SimpleAssignment result = instance.createAssignment(min, max);
            assertTrue(result.getFirstNumber() >= min && result.getFirstNumber() <= max);
            assertTrue(result.getSecondNumber() >= min && result.getSecondNumber() <= max);
            assertEquals(result.getSolution(), result.getFirstNumber() * result.getSecondNumber());
        }
    }
    
    @org.junit.jupiter.api.Test
    public void testCreateNegativeAssignment()
    {
        int min = -25;
        int max = 25;
        MultiplicationService instance = new MultiplicationService();
        
        for (int i = 0; i < 10; ++i) 
        {
            SimpleAssignment result = instance.createAssignment(min, max);
            assertTrue(result.getFirstNumber() >= min && result.getFirstNumber() <= max);
            assertTrue(result.getSecondNumber() >= min && result.getSecondNumber() <= max);
            assertEquals(result.getSolution(), result.getFirstNumber() * result.getSecondNumber());
        }
    }
}
