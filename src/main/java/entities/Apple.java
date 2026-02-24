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
public class Apple extends Food<Apple>{
    public Apple(){super();}
    public Apple(float initial){super(initial);}
    public Apple(Scalar initial){super(initial);}
    public Apple(Apple initial){super(initial);}
    
}