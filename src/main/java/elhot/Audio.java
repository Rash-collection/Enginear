/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elhot;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author rash4
 */
public final class Audio {
    public Audio(String file){
        final URL lnk = this.getClass().getResource("/media/sound/" + file + ".wav");
        if (lnk == null) 
            throw new IllegalArgumentException("Sound file not found: " + file);
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(lnk)) {
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Failed to load sound: " + file, e);
        }
    }
    public void play() {
        if (this.clip.isRunning()) {
            this.clip.stop();
        }
        this.clip.setFramePosition(0);
        this.clip.start();
    }
    public void stop() {
        this.clip.stop();
        this.clip.setFramePosition(0);
    }
    public void loop() {
        this.clip.setFramePosition(0);
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    private final Clip clip;
}