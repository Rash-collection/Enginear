/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package setups;

/**
 *
 * @author rash4
 */
public final class StartupLog {
    private static final StringBuffer FALLBACK = new StringBuffer();
    private StartupLog() {}
    public static void log(Throwable t) {
        try {
            FALLBACK.append(format(t));
            // optional: try file logging here later
        } catch (Throwable ignored) {
            // NEVER throw from logging
        }
    }
    public static void log(String msg) {
        try {
            FALLBACK.append(msg).append('\n');
        } catch (Throwable ignored) {}
    }
    public static String dumpFallback() {
        return FALLBACK.toString();
    }
    private static String format(Throwable t) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("\n--- ").append(t.getClass().getName()).append(" ---\n");
        sb.append(t.getMessage()).append('\n');
        for (StackTraceElement e : t.getStackTrace()) {
            sb.append("  at ").append(e).append('\n');
        }
        return sb.toString();
    }
}