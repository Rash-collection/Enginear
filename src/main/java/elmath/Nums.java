/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elmath;

/**
 *
 * @author rash4
 */
public class Nums {
    public Nums(int value){
        this.value = value;
    }
    public void postIncrement(){this.value++;}
    public void postDecrement(){this.value--;}
    public void preIncrement(){this.value++;}
    public void preDecrement(){this.value--;}
    public int value;
}