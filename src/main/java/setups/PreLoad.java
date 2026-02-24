/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package setups;

import means.Initializing;
import means.Launcher;

/**
 *
 * @author rash4
 */
public final class PreLoad {
    private PreLoad(){}
    
    public static void resources(){
        elfactory.Materializing.preLoad();
        
        Initializing.animate();
    }
    public static void load(){
        Launcher.GAME.preLoad();
        Launcher.GAME.setIcon(elfactory.Materializing.icon());
        javax.swing.SwingUtilities.invokeLater(()->{
            Launcher.console().preLoad();
            Launcher.setConsole();
            
            Initializing.debuqer();
            Initializing.daPlay();
            Initializing.displayShelf();
            Initializing.commandSeries();
        });
        javax.swing.SwingUtilities.invokeLater(()->{Launcher.launch(2);});
        javax.swing.SwingUtilities.invokeLater(Launcher.LOOP::start);
    }
}