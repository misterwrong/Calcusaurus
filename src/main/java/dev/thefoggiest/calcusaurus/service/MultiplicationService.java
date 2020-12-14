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
import java.util.Collections;
import java.util.List;
import dev.thefoggiest.calcusaurus.model.SimpleAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleMultiplicationAssignment;
import dev.thefoggiest.calcusaurus.util.NumberUtil;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class MultiplicationService implements AssignmentService
{
    /**
     * Returns an assignment where the multiplication of the first and second 
     * number is the solution. All three numbers are integers between the min 
     * and max given.
     * 
     * @param min The minimum of all values in the assignment
     * @param max The maximum of all values in the assignment
     * @return 
     */
    @Override
    public SimpleAssignment createAssignment(int min, int max)
    {
        SimpleAssignment m = new SimpleMultiplicationAssignment();
        
        // Rule out primes, or most of the assignments will be 1 * x = x
        List<Integer> factors = new ArrayList<>();
        while (factors.size() < 3) {
            m.setSolution(NumberUtil.getRandomInt(min, max));
            factors = NumberUtil.findFactors(m.getSolution());
        }
        Collections.shuffle(factors);
            
        m.setFirstNumber(factors.get(0));
        m.setSecondNumber(m.getSolution()/ m.getFirstNumber());
        
        return m;
    }
}
