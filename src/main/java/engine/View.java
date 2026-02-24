/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package engine;

import elmath.Ranged;
import elmath.Vector;
import java.awt.Point;
import java.awt.Rectangle;
import means.Launcher;

/**
 *
 * @author rash4
 */
public class View {
    public View(){}
    public View(Rectangle bounds){
        if(bounds == null)
            throw new IllegalArgumentException(
                    "Null argument in the constructor.");
        this.boundary = new Ranged(bounds);
        this.pivot = bounds.getLocation();
        this.marg = new Point();
    }
    public View resizeView(Rectangle neo){
        this.setPivot(neo.getLocation()).boundary.setExtent(neo.width, neo.height);
        return this;
    }
    public View setPivot(Point neo){return this.setPivot(neo.x, neo.y);}
    public View setPivot(int x, int y){
        this.pivot.x = x;
        this.pivot.y = y;
        return this;
    }
    public View setBounds(Ranged bounds){
        this.boundary = bounds;
        return this;
    }
    public View setBounds(Rectangle bounds){
        this.boundary = new Ranged(bounds);
        return this;
    }
    public View setMarg(int x, int y){
        this.marg.setLocation(x, y);
        return this;
    }
    public Point getPivot(){return this.pivot;}
    public Point getMarg (){return this.marg;}
    public Ranged getBounds(){return this.boundary;}
//    public Ranged getBounds(){return this.boundary;}
//    public Rectangle zoomer(){return this.boundary.rectangle();}
    public Rectangle projector(){
        return this.boundary.rectangle();
    }
    public Rectangle zoomer(){
//        final var bnd = 
                return new Ranged(this.boundary.getCenter(), 
                new Vector(this.boundary.getRadius().add(this.marg.x, this.marg.y).scale(1F/Launcher.escalator))).rectangle();
//        final int xx = bnd.x - this.marg.x,
//                  yy = bnd.y - this.marg.y,
//                  ww = bnd.width + (this.marg.x*2),
//                  hh = bnd.height+ (this.marg.y*2);
//        return new Rectangle(xx, yy, ww, hh);
    }
    public Rectangle screen(){return new Rectangle(this.pivot, this.boundary.dimension());}
    /**it's kinda intentional to be a delta between pivotal and center instead of corner.<br> maybe fix it later if needed be!*/
    public Point getDelta(Rectangle zoom){
        final var esc = Launcher.escalator;
//        return this.boundary.getCorner().sub(new Vector(this.pivot.x, this.pivot.y)).scale(Launcher.escalator).point();
        final var cor = zoom.getLocation();
        final int xl = this.pivot.x - this.marg.x,
                yl = this.pivot.y - this.marg.y;
//        final var cor = this.boundary.getCorner().point();
        return new Point(cor.x - xl, cor.y - yl);
    }
    private Point pivot, marg;
    private Ranged boundary;
}