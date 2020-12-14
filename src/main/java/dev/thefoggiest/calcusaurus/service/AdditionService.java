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

import dev.thefoggiest.calcusaurus.model.SimpleAdditionAssignment;
import dev.thefoggiest.calcusaurus.model.SimpleAssignment;
import dev.thefoggiest.calcusaurus.util.NumberUtil;

/**
 *
 * @author Mister Wrong <hello@thefoggiest.dev>
 */
public class AdditionService implements AssignmentService
{

    /**
     * Creates a simple addition assignment where the solution is the addition of the first and 
     * second number and all three are integers between min and max given.
     * 
     * @param min The minimum of the three numbers
     * @param max The maximum of the three numbers
     * @return 
     */
    @Override
    public SimpleAssignment createAssignment(final int min, final int max)
    {
        // if min = 0, get random nrs 1 - max, substract 1 from everything afterwards
        // if min = -x, get random nrs 1 - max * 2, substract max from everything afterwards
        // minimal difference must be 1 if 0 is not allowed, since we're starting from the solution
        int minimalDifference = min == 0 ? 0 : 1;

        SimpleAssignment addition = new SimpleAdditionAssignment();

        addition.setSolution(NumberUtil.getRandomInt(min + minimalDifference, max));
        addition.setFirstNumber(NumberUtil.getRandomInt(min, addition.getSolution() - minimalDifference));
        addition.setSecondNumber(addition.getSolution() - addition.getFirstNumber());

        return addition;
    }
}
