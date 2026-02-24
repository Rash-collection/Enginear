/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import elmath.Scalar;
/**
 *
 * @author rash4
 */
public class Rock extends Mineral<Rock>{
    public Rock(){super();}
    public Rock(float initial){super(initial);}
    public Rock(Scalar initial){super(initial);}
    public Rock(Rock initial){super(initial);}
    
}