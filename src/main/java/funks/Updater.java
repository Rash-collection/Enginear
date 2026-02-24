/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package funks;

/**
 *
 * @author rash4
 */
@FunctionalInterface
public interface Updater {
    public void update();
    public default int getIndicator(){return means.Launcher.indicator;}
}