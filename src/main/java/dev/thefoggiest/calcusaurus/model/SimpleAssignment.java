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
package dev.thefoggiest.calcusaurus.model;

import java.util.ResourceBundle;

/**
 *
* @author Mister Wrong <hello@thefoggiest.dev>
 */
public abstract class SimpleAssignment implements Assignment {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("Bundle");

    private int firstNumber;
    private int secondNumber;
    private int solution;

    @Override
    public int getFirstNumber() {
        return firstNumber;
    }

    @Override
    public int getSecondNumber() {
        return secondNumber;
    }

    @Override
    public int getSolution() {
        return solution;
    }    

    /**
     * @param firstNumber the firstNumber to set
     */
    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    /**
     * @param secondNumber the secondNumber to set
     */
    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    /**
     * @param solution the solution to set
     */
    public void setSolution(int solution) {
        this.solution = solution;
    }
    
    @Override
    public String getAssignment() {
        return getFirstNumber() + " " + getSymbol() + " " + getSecondNumber();
    }
    
    @Override
    public String toString() {
        return getFirstNumber() + " " + getSymbol() + " " + getSecondNumber() + " = " + getSolution();
    }
    
    @Override
    public boolean equals(Object other) {
        if (null == other) 
        {
            return false;
        }
        if (!(other instanceof SimpleAssignment)) 
        {
            throw new IllegalArgumentException(bundle.getString("ONLY LIKE EXERCICES"));
        }
        var otherAssignment = (SimpleAssignment)other;
        return this.firstNumber == otherAssignment.getFirstNumber() 
            && this.secondNumber == otherAssignment.getSecondNumber();
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + this.firstNumber;
        hash = 29 * hash + this.secondNumber;
        return hash;
    }
}
