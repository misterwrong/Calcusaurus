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

import java.util.Collections;
import dev.thefoggiest.calcusaurus.model.SimpleAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleDivisionAssignment;
import dev.thefoggiest.calcusaurus.util.NumberUtil;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class DivisionService implements AssignmentService
{
    /**
     * Creates a simple division assignment where the solution is always an 
     * integer with no remainder.
     *
     * @param min
     * @param max
     * @return
     */
    @Override
    public SimpleAssignment createAssignment(int min, int max)
    {
        SimpleAssignment d = new SimpleDivisionAssignment();
        
        d.setFirstNumber(NumberUtil.getRandomDenominator(min, max));
        var factors = NumberUtil.findFactors(d.getFirstNumber());        
        Collections.shuffle(factors);
        
        d.setSecondNumber(factors.get(0));
        d.setSolution(d.getFirstNumber() / d.getSecondNumber());        
        
        return d;
    }
}
