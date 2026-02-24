/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elhot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.sound.sampled.*;
import setups.AbsHandler;
import setups.StartupThrowable;

/**
 *
 * @author rash4
 */
public final class AudioHandler extends AbsHandler<byte[]> {
    public AudioHandler(Path root) { super(root); }
    @Override public void save(byte[] data, String name) throws StartupThrowable {
        Path dir = root.resolve("sounds");
        ensureDir(dir);
        Path file = dir.resolve(name);
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(data);
        } catch (IOException e) {
            logAndThrow("Failed to save audio: " + name, e);
        }
    }
    @Override public byte[] load(String name) throws StartupThrowable {
        Path file = root.resolve("audio").resolve(name);
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            logAndThrow("Failed to load audio: " + name, e);
            return null;
        }
    }
    public void play(byte[] data) throws StartupThrowable {
        try (AudioInputStream ais = AudioSystem.
                getAudioInputStream(new ByteArrayInputStream(data))) {
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            logAndThrow("Failed to play audio", e);
        }
    }
}