/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import funks.Viewable;
import elmath.Vector;
import funks.Resizer;
import funks.Updater;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import means.Launcher;

/**
 *
 * @author rash4
 */
public class Player extends Bounded<Player> 
        implements Updater, Viewable, Resizer, Boxable, Drawable{
    public Player(){
        super(new Rectangle(new Point(128, 128), new Dimension(64, 64)));
        this.name = "Guest";
        this.nrt = this.wst = this.sut = this.est = false;
        this.target = new Vector(this.boundary.getCenter());
    }
    
    
    @Override public void update() {
    }
    @Override public void render(Graphics2D grr, Rectangle range, Point delta) {
        if(!range.contains(this.boundary.getCenter().point()))return;
        final var bnd = this.boundary.scale(Launcher.escalator).rectangle();
        final int xx = bnd.x - delta.x, yy = bnd.y - delta.y;
        final int lfw = bnd.width/2, lfh = bnd.height/2;
        grr.drawImage(this.sprite, xx, yy, bnd.width, bnd.height, null);
        grr.setColor(Color.BLACK);
        grr.drawRect(xx, yy, bnd.width, bnd.height);
        grr.drawRoundRect(
                (int)this.target.x() - lfw - delta.x, (int)this.target.y() - lfh - delta.y,
                bnd.width, bnd.height, 8, 8);
    }
    @Override public void resize() {
    }
    @Override public Rectangle hitBox() {
        return this.boundary.rectangle();
    }
    
    public void setSprite(BufferedImage spt){
        this.sprite = spt;
    }
    
    public void setNort(boolean val){this.nrt = val;}
    public void setWest(boolean val){this.wst = val;}
    public void setSout(boolean val){this.sut = val;}
    public void setEast(boolean val){this.est = val;}
    
    /**this never gonna be true, because they're equals only at the very start (construct phase)*/
    public boolean moving(){return !this.boundary.getCenter().equals(this.target);}
    
    public boolean nort(){return this.nrt;}
    public boolean west(){return this.wst;}
    public boolean sout(){return this.sut;}
    public boolean east(){return this.est;}
    
    @Override public BufferedImage sprite(){return this.sprite;}
    public String name(){return this.name;};
    @Override public String toString(){return "Player " + this.name;}
    
    private final String name;
    @annots.Note("try a better and effecient way.")
    public Vector target;
    private boolean nrt, wst, sut, est;
    private BufferedImage sprite;
}