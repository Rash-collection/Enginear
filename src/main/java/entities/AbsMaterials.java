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
public abstract class AbsMaterials <T extends AbsMaterials<T>>{
    protected AbsMaterials(){
        this.mats = new Scalar(0F);
    }
    protected AbsMaterials(float initial){
        this.mats = new Scalar(initial);
    }
    protected AbsMaterials(Scalar initial){
        this.mats = new Scalar(initial);
    }
    protected AbsMaterials(T initial){
        this.mats = new Scalar(initial.mats);
    }
    
    public T self(){return (T)this;}
    
    public Scalar amount(){return this.mats;}
    
    protected final Scalar mats;
}