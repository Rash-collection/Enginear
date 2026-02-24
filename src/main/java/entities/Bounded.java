/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import elmath.Ranged;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author rash4
 * @param <T>       Entity type extension.
 */
public abstract class Bounded <T extends Bounded<T>> extends Entity<T>{
    public final static Dimension DEF_SIDS = new Dimension(64, 64);
    public Bounded(){
        super();
        this.boundary = new Ranged(new Rectangle(DEF_SIDS));
    }
    public Bounded(Rectangle bounds){
        super();
        this.boundary = new Ranged(bounds);
    }
    public Bounded(int x, int y, int w, int h){
        super();
        this.boundary = new Ranged(x, y, w, h);
    }
    public Ranged boundary;
}