/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elmath;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author rash4
 */
public final record Vector(float x, float y) implements Comparable<Vector>{
    public final static Vector ZERO = new Vector(0F, 0F);
    public Vector(Vector neo){
        this(neo.x, neo.y);
    }
    public Vector addX(float xx){return new Vector(this.x + xx, this.y);}
    public Vector addY(float yy){return new Vector(this.x, this.y + yy);}
    public Vector subX(float xx){return new Vector(this.x - xx, this.y);}
    public Vector subY(float yy){return new Vector(this.x, this.y - yy);}
    public Vector add(float xx, float yy){return new Vector(this.x + xx, this.y + yy);}
    public Vector sub(float xx, float yy){return new Vector(this.x - xx, this.y - yy);}
    
    public Vector addX(Vector neo){return new Vector(this.x + neo.x, this.y);}
    public Vector addY(Vector neo){return new Vector(this.x, this.y + neo.y);}
    public Vector subX(Vector neo){return new Vector(this.x - neo.x, this.y);}
    public Vector subY(Vector neo){return new Vector(this.x, this.y - neo.y);}
    public Vector add(Vector neo){return new Vector(this.x + neo.x, this.y + neo.y);}
    public Vector sub(Vector neo){return new Vector(this.x - neo.x, this.y - neo.y);}
    
    public Vector scaleX(float scaler){return new Vector(this.x * scaler, this.y);}
    public Vector scaleY(float scaler){return new Vector(this.x, this.y * scaler);}
    public Vector scale(float scaler){return new Vector(this.x * scaler, this.y * scaler);}
    
    public double dot(Vector neo){return this.x * neo.x + this.y * neo.y;}
    public double cross(Vector neo){return this.x * neo.y - this.y * neo.x;}
    public Vector normalize(){
        double len = length();
        if (len == 0) return this;
        return scale((float)(1.0 / len));
    }
    public double length(){return Math.sqrt((this.x*this.x) + (this.y*this.y));}
    public double lengthSquared(){return this.x * this.x + this.y * this.y;}
    public int compareByLength(Vector neo){return Double.compare(this.lengthSquared(), neo.lengthSquared());}
    public double distance(Vector neo){
        final double xx = this.x - neo.x, yy = this.y - neo.y;
        return Math.sqrt((xx*xx)+(yy*yy));
    }
    public double angle(){return Math.atan2(this.y, this.x);}
    public double angle(Vector neo){
        double lenProd = this.length() * neo.length();
        return lenProd == 0 ? 0 : Math.acos(dot(neo) / lenProd);
    }
    public Point point(){return new Point(Math.round(this.x), Math.round(this.y));}
    public Dimension dimension(){return new Dimension(Math.round(this.x), Math.round(this.y));}
    
    @Override public int compareTo(Vector neo){return this.compareByLength(neo);}
    public boolean equals(Vector neo, float epsilon) {
        if (neo == null) return false;
        return Math.abs(this.x - neo.x) <= epsilon
            && Math.abs(this.y - neo.y) <= epsilon;
    }
    public boolean near(Vector neo, float epsilon) {
        return this.sub(neo).lengthSquared() <= epsilon * epsilon;
    }
    
}