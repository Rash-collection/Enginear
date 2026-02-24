/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import funks.Viewable;
import funks.Resizer;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import means.Launcher;

/**
 * <h3>for permanent background.</h3>
 * @author rash4
 */
public class Terrain <T extends Terrain<T>> extends Bounded<T> 
        implements  Drawable, Viewable, Resizer{
    public Terrain(){
        super();
    }
    public Terrain(Rectangle bounds){
        super(bounds);
    }
    
    public T setImage(BufferedImage img){
        this.sprite = img;
        return this.self();
    }
    
    @Override public void render(Graphics2D grr, Rectangle bnd, Point del, Rectangle cull) {
        if(!cull.contains(this.boundary.getCenter().point()))return;
        final var bnds = this.boundary.scale(Launcher.escalator).rectangle();
        synchronized(this.lock()){
            grr.drawImage(this.sprite, bnds.x - del.x, bnds.y - del.y, bnds.width, bnds.height, null);
        }
    }
    @Override public void resize() {
    }
    @Override public BufferedImage sprite() {return this.sprite;}
    
    protected BufferedImage sprite;
}