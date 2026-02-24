/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elhot;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * <p>If ever confused just look at the three main methods.</p>
 * Main  three methods...
 * @see         #resourceText(java.lang.String)
 * @see         #resourceImage(java.lang.String)
 * @see         #resourceAudio(java.lang.String)
 * @author rash4
 */
public final class IOS {
    private static final ClassLoader CL = IOS.class.getClassLoader();
    private IOS() {}
    /* =========================
       CLASSPATH RESOURCES
       ========================= */
    public static InputStream resource(String path) {
        InputStream in = CL.getResourceAsStream(path);
        if (in == null)
            throw new IllegalArgumentException("Missing resource: " + path);
        return in;
    }
    public static URL resourceURL(String path) {
        URL url = CL.getResource(path);
        if (url == null)
            throw new IllegalArgumentException("Missing resource: " + path);
        return url;
    }
    public static String resourceText(String path) {
        return resourceText(path, StandardCharsets.UTF_8);
    }
    public static String resourceText(String path, Charset cs) {
        try (InputStream in = resource(path)) {
            return new String(in.readAllBytes(), cs);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public static Properties resourceProperties(String path) {
        try (InputStream in = resource(path)) {
            Properties p = new Properties();
            p.load(in);
            return p;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public static BufferedImage resourceImage(String path) {
        try (InputStream in = resource(path)) {
            return ImageIO.read(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public static AudioInputStream resourceAudio(String path) {
        try {
            return AudioSystem.getAudioInputStream(resource(path));
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException("Failed to load audio: " + path, e);
        }
    }
    /* =========================
       FILESYSTEM (RUNTIME DATA)
       ========================= */
    public static InputStream file(Path path) {
        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public static String fileText(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public static void writeText(Path path, String text) {
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, text);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}