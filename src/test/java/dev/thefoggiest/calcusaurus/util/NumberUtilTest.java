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
package dev.thefoggiest.calcusaurus.util;

import dev.thefoggiest.calcusaurus.util.NumberUtil;
import java.util.Arrays;
import static org.junit.Assert.*;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class NumberUtilTest
{
    /**
     * Test of getInt method, of class NumberUtil.
     */
    @org.junit.jupiter.api.Test
    public void testGetInt()
    {
        assertEquals(NumberUtil.getInt("1"), 1);
        assertEquals(NumberUtil.getInt("1"), 1);
        assertEquals(NumberUtil.getInt("1 "), 1);
        assertEquals(NumberUtil.getInt(" 1"), 1);
        assertEquals(NumberUtil.getInt(" 1 "), 1);
        assertEquals(NumberUtil.getInt("14"), 14);
        assertEquals(NumberUtil.getInt("145"), 145);
        assertEquals(NumberUtil.getInt("145"), 145);
        assertEquals(NumberUtil.getInt("-14"), -14);
    }

    @org.junit.jupiter.api.Test
    public void testGetRandomPositiveInt()
    {
        int min = 1;
        int max = 10;

        int n = NumberUtil.getRandomInt(min, max);
        assertTrue(n >= min);
        assertTrue(n <= max);
    }

    @org.junit.jupiter.api.Test
    public void testGetRandomNullableInt()
    {
        int min = 0;
        int max = 10;

        for (int i = 0; i < 25; ++i)
        {
            int n = NumberUtil.getRandomInt(min, max);
            assertTrue(n >= min);
            assertTrue(n <= max);
        }
    }

    @org.junit.jupiter.api.Test
    public void testGetRandomNegativeInt()
    {
        int min = -10;
        int max = 10;

        for (int i = 0; i < 25; ++i)
        {
            int n = NumberUtil.getRandomInt(min, max);
            assertTrue(n >= min);
            assertTrue(n <= max);
        }
    }

    @org.junit.jupiter.api.Test
    public void testGetRandomEqualNegative()
    {
        int min = -1;
        int max = -1;

        int n = NumberUtil.getRandomInt(min, max);
        assertTrue(n >= min);
        assertTrue(n <= max);
    }

    @org.junit.jupiter.api.Test
    public void testGetRandomEqualZero()
    {
        int min = 0;
        int max = 0;

        int n = NumberUtil.getRandomInt(min, max);
        assertTrue(n >= min);
        assertTrue(n <= max);
    }

    @org.junit.jupiter.api.Test
    public void testGetRandomEqualPositive()
    {
        int min = 1;
        int max = 1;

        int n = NumberUtil.getRandomInt(min, max);
        assertTrue(n >= min);
        assertTrue(n <= max);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetRandomDenominator() {
        for (int i = 0; i < 25; ++i) 
        {
            assertFalse(0 == NumberUtil.getRandomDenominator(-10, 10));
        }
    }

    @org.junit.jupiter.api.Test
    public void testFindFactorsOf8()
    {
        var factors = Arrays.asList(1, 2, 4, 8);
        var found = NumberUtil.findFactors(8);
        assertEquals(factors, found);
    }
    
    @org.junit.jupiter.api.Test
    public void testFindFactorsOf15()
    {
        var factors = Arrays.asList(1, 3, 5, 15);
        var found = NumberUtil.findFactors(15);
        assertEquals(factors, found);
    }
    
    @org.junit.jupiter.api.Test
    public void testFindFactorsOfZero()
    {
        var factors = Arrays.asList(0);
        var found = NumberUtil.findFactors(0);
        assertEquals(factors, found);
    }
    
    @org.junit.jupiter.api.Test
    public void testFindNegativeFactors()
    {
        var factors = Arrays.asList(-1, -3, -5, -15);
        var found = NumberUtil.findFactors(-15);
        assertEquals(factors, found);
    }
}
