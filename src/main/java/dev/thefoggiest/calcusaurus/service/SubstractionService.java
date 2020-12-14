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

import dev.thefoggiest.calcusaurus.model.SimpleAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleSubstractionAssignment;
import dev.thefoggiest.calcusaurus.util.NumberUtil;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class SubstractionService implements AssignmentService {

    /**
     * Creates a substraction assigment where the first number is higher than the second number so 
     * that the solution is a positive number. When the mininum number is 0 or less, 0 will also be 
     * allowed as the solution.
     * 
     * @param max 
     * @param min
     * @return 
     */
    @Override
    public SimpleAssignment createAssignment(int min, int max) {
        
        int minDiff = min <= 0 ? 0 : 1;
        
        SimpleAssignment s = new SimpleSubstractionAssignment();
        
        int n1 = NumberUtil.getRandomInt(min + minDiff, max);
        
        int min2, max2;
        if (min >= 0) 
        {
            min2 = min;
            max2 = n1 - minDiff;
        }
        else 
        {
            min2 = n1 + min < min ? min : n1 + min;
            max2 = n1 + max > max ? max : n1 + max;
        }
        
        int n2 = NumberUtil.getRandomInt(min2, max2);
        
        s.setFirstNumber(n1);
        s.setSecondNumber(n2);
        s.setSolution(n1 - n2);
  
        return s;
    }    
}
