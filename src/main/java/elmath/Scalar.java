/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elmath;

/**
 *
 * @author rash4
 */
public final class Scalar {
    public Scalar(float value){
        this.value = value;
    }
    public Scalar(Scalar neo){
        if(neo == null) throw new NullPointerException(
                "the neo in the Scalar-constructor is null.");
        this.value = neo.value;
    }
    
    public float scale(Scalar nefo){
        return this.scale(nefo.value);
    }
    public float scale(float factor){
        return this.value * factor;
    }
    public void multiply(Scalar nefo){
        this.multiply(nefo.value);
    }
    public void multiply(float factor){
        this.value *= factor;
    }
    
    public float take(Scalar neo){return this.take(neo.value);}
    public float take(float amount){
        float taken = Math.min(this.value, amount);
        this.value -= taken;
        return taken;
    }
    public void add(Scalar neo){this.add(neo.value);}
    public void add(float amount){this.value += amount;}
    
    public boolean isEmpty(){return this.value <= 0f;}
    public float get(){ return this.value; }
    
    private float value;
}