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
public class Food <T extends AbsMaterials<T>> extends AbsMaterials<T>{
    protected Food(){super();}
    protected Food(float initial){super(initial);}
    protected Food(Scalar initial){super(initial);}
    protected Food(T initial){super(initial);}
    
}