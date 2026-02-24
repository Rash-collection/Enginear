/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package setups;

import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author rash4
 */
public final class StartupResources {
    private StartupResources() {}
    public static InputStream stream(String path) {
        InputStream in = StartupResources.class
                .getClassLoader()
                .getResourceAsStream(path);
        if (in == null) {
            throw new IllegalArgumentException(
                "Classpath resource not found: " + path
            );
        }
        return in;
    }
    public static URL url(String path) {
        URL url = StartupResources.class
                .getClassLoader()
                .getResource(path);
        if (url == null) {
            throw new IllegalArgumentException(
                "Classpath resource not found: " + path
            );
        }
        return url;
    }
}