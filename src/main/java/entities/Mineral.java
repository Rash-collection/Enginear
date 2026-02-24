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
public class Mineral <T extends AbsMaterials<T>> extends AbsMaterials<T>{
    protected Mineral(){super();}
    protected Mineral(float initial){super(initial);}
    protected Mineral(Scalar initial){super(initial);}
    protected Mineral(T initial){super(initial);}
    
}