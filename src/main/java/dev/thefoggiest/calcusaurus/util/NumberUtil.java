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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class NumberUtil
{
    private static final ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
    
    public static int getInt(String s)
    {
        if (s.isBlank()) {
            throw new IllegalArgumentException(bundle.getString("INVOER MAG NIET LEEG ZIJN"));
        }
        return Integer.parseInt(s.strip());
    }
    
    /**
     * Returns a non zero random integer between min and max.
     * 
     * @param min The minimum (inclusive) value of the returned number
     * @param max The maximum (inclusive) value of the returned number
     * @return 
     */
    public static int getRandomDenominator(int min, int max) 
    {
        if (min == 0) 
        {
            throw new IllegalArgumentException(bundle.getString("DELEN DOOR NUL KAN NIET"));
        }
        
        if (min > max) 
        {
            throw new IllegalArgumentException(bundle.getString("MIN KAN NIET HOGER ZIJN DAN MAX"));
        }
        
        final ThreadLocalRandom random = ThreadLocalRandom.current();

        int n;

        if (min < 0)
        {
            return random.nextInt(1, max + (1 - min));
        }
        else
        {
            n = random.nextInt(min, max);
        }
        return n;
    }

    /**
     * Returns a random integer between min and max given.
     * 
     * @param min The minimum (inclusive) value of the returned number
     * @param max The maximum (inclusive) value of the returned number
     * @return 
     */
    public static int getRandomInt(int min, int max)
    {
        if (min == max)
        {
            return min;
        }
        else if (min > max) 
        {
            throw new IllegalArgumentException(bundle.getString("MIN KAN NIET HOGER ZIJN DAN MAX"));
        }

        final ThreadLocalRandom random = ThreadLocalRandom.current();

        int n;

        if (min == 0)
        {
            return (random.nextInt(min + 1, max + 1)) - 1;
        }
        else if (min < 0)
        {
            return (random.nextInt(1, max + (1 - min))) - (1 - min);
        }
        else
        {
            n = random.nextInt(min, max);
        }
        return n;
    }

    public static List<Integer> findFactors(int n)
    {
        if (0 == n)
        {
            return Arrays.asList(0);
        }

        boolean negative = n < 0;

        var factors = new ArrayList<Integer>();
        int factor = negative ? -1 : 1;

        if (negative)
        {
            while (factor >= n)
            {
                if (n % factor == 0)
                {
                    factors.add(factor);
                }
                factor--;
            }
        }
        else
        {
            while (factor <= n)
            {
                if (n % factor == 0)
                {
                    factors.add(factor);
                }
                factor++;
            }
        }
        
        return factors;
    }
}
