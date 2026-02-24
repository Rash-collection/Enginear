/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import funks.Updater;
import java.awt.Rectangle;

/**
 * <h3>for permanent and interactive background.</h3>
 * @author rash4
 */
public class ActiveTerrain <T extends ActiveTerrain<T>> extends Terrain<T> 
        implements Boxable, Updater{
    public ActiveTerrain(){
        super();
        this.hitbox = this.boundary::rectangle;
    }
    public ActiveTerrain(Rectangle bounds){
        super(bounds);
        this.hitbox = this.boundary::rectangle;
    }
    
    @Override public void update() {
        
    }
    
    public T setHitbox(Boxable box){
        if(box != null) this.hitbox = box;
        return this.self();
    }
    @Override public Rectangle hitBox() {return this.hitbox.hitBox();}
    
    private Boxable hitbox;
}