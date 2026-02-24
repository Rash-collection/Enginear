/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package funks;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 * @author rash4
 */
@FunctionalInterface
public interface Viewable extends Renderer{
    @Override default void render(Graphics2D grr){
        throw new UnsupportedOperationException(
                "This instance is View-aware, didn't implement the one-param render method.");
    }
    void render(Graphics2D grr, Rectangle screening, Point delta, Rectangle cull);
}