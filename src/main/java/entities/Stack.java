/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import funks.Viewable;
import funks.Updater;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import means.Launcher;

/**
 *
 * @author rash4
 * @param <T>       Stack type
 * @param <U>       Bounded type
 */
public abstract class Stack <T extends Stack<T, U>, U extends AbsMaterials<U>> extends Bounded<T> 
        implements Drawable, Viewable, Boxable, Updater, Exhaustable{
    public Stack(){super();}
    public Stack(Rectangle bounds){super(bounds);}
    public Stack(int x, int y, int w, int h){super(x, y, w, h);}
    /**if not implemented, it return true when it's empty amount!!*/
    @Override public boolean consumed() {return this.source.mats.isEmpty();}
    
    @Override public void render(Graphics2D grr, Rectangle vew, Point del, Rectangle cull) {
        if(!cull.contains(this.boundary.getCenter().point())) return; // early skip
        final var bnd = this.boundary.scale(Launcher.escalator).rectangle();
        synchronized(this.lock()){
            grr.drawImage(this.sprite,
                    bnd.x - del.x, bnd.y - del.y, 
                    bnd.width, bnd.height, null);
        }
    }
    @Override public void update() {
    }
    
    /**if not implemented, it returns the visual bounds as defaults.*/
    @Override public Rectangle hitBox() {return this.boundary.rectangle();}
    @Override public BufferedImage sprite() {return this.sprite;}
    
    public U getSource(){return this.source.self();}
    
    protected AbsMaterials<U> source;
    protected BufferedImage sprite;
}