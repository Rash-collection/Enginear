/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package engine;

import java.awt.Graphics2D;

/**
 *
 * @author rash4
 */
public class Rendering {
    private Rendering(){}
    
    public static void render(Graphics2D grr){
        means.Launcher.currentScene().render.render(grr);
    }
    public static void render(){
        means.Launcher.repaint();
    }
}