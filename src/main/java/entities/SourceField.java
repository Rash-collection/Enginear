/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import java.awt.Rectangle;

/**
 *
 * @author rash4
 */
public class SourceField <T extends SourceField<T, U>, U extends AbsMaterials<U>> extends ActiveTerrain<T>{
    public SourceField(){
        super();
        
    }
    public SourceField(Rectangle bounds){
        super(bounds);
        
    }
    
    public U getResource(){return this.resource.self();}
    
    protected AbsMaterials<U> resource;
}