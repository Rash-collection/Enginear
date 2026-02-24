/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package setups;

/**
 *
 * @author rash4
 */
public class StartupThrowable extends Throwable {
    public StartupThrowable(String message) {
        super(message);
        StartupLog.log(this);
    }
    public StartupThrowable(String message, Throwable cause) {
        super(message, cause);
        StartupLog.log(this);
    }
    public StartupThrowable(Throwable cause) {
        super(cause);
        StartupLog.log(this);
    }
}