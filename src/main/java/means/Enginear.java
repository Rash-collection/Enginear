/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package means;

import elhot.TextHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import setups.StartupPaths;
import setups.StartupStructure;
import setups.StartupThrowable;

/**
 *
 * @author rash4
 */
public class Enginear {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        StartupPaths.setAppName("MAWE");
        final var rt = StartupPaths.root();
        StartupStructure.createAllDirs();
        
        setups.PreLoad.resources();
        
//        final var tt = new TextHandler(rt);
//        try {
//            tt.save("aloha this is mawe test", "maw.txt");
//        } catch (StartupThrowable ex) {
//            Logger.getLogger(Enginear.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        setups.PreLoad.load();
    }
}