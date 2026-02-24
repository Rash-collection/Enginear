/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elmath;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * <h2>Vector based Range, with center point and radius as range.</h2>
 * @author rash4
 */
public final class Ranged {
    public Ranged(Ranged neo){
        if(neo == null) 
            throw new IllegalArgumentException(
                "Null argument in the copy constructor.");
        this.center = neo.center;
        this.radius = neo.radius;
    }
    public Ranged(Vector center, Vector radius){
        if(center == null || radius == null) 
            throw new IllegalArgumentException(
                "Null argument in the constructor.");
        this.center = center;
        this.radius = radius;
    }
    public Ranged(Rectangle neo){
        if(neo == null)
            throw new IllegalArgumentException(
                "Null argument in the constructor.");
        this.setExtent(neo.width, neo.height).setCorner(neo.x, neo.y);
    }
    public Ranged(int x, int y, int w, int h){
        this.radius = new Vector(w/2F, h/2F);
        this.center = new Vector(x + this.radius.x(), y + this.radius.y());
    }
    
    public Vector getCenter(){return this.center;}
    public Vector getRadius(){return this.radius;}
    public Vector getCorner(){return this.center.sub(this.radius);}
    public Vector getOther(){return this.center.add(this.radius);}
    public Vector getExtent(){return this.radius.scale(2);}
    
    public Point point(){return this.getCorner().point();}
    public Point otherPoint(){return this.getOther().point();}
    public Dimension dimension(){return this.getExtent().dimension();}
    public Rectangle rectangle(){return new Rectangle(this.point(), this.dimension());}
    
    public Ranged setCenter(Vector neo){this.center = neo; return this;}
    public Ranged setRadius(Vector neo){this.radius = neo; return this;}
    public Ranged setCorner(float xx, float yy){
        this.center = new Vector(xx, yy).add(this.radius);
        return this;
    }
    public Ranged scale(float facto){
        return new Ranged(this.center.scale(facto), this.radius.scale(facto));
    }
    public Ranged setExtent(float ww, float hh){
        this.radius = new Vector(ww/2F, hh/2);
        return this;
    }
    public boolean contains(Point point){
        return this.rectangle().contains(point);
    }
    public boolean contains(Ranged other){
        return this.contains(other.rectangle());
    }
    public boolean contains(Rectangle other){
        return this.rectangle().contains(other);
    }
    public boolean intersects(Ranged other){
        return this.rectangle().intersects(other.rectangle());
    }
    private Vector center, radius;
}