/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elhot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import setups.AbsHandler;
import setups.StartupThrowable;

/**
 *
 * @author rash4
 */
public final class TextHandler extends AbsHandler<String> {
    public TextHandler(Path root) {
        super(root);
    }
    @Override public void save(String data, String name) throws StartupThrowable {
        Path dir = root.resolve("texts");
        ensureDir(dir);
        try {
            Files.writeString(dir.resolve(name), data);
        } catch (IOException e) {
            logAndThrow("Failed to save text: " + name, e);
        }
    }
    @Override public String load(String name) throws StartupThrowable {
        Path file = root.resolve("text").resolve(name);
        try {
            return Files.readString(file);
        } catch (IOException e) {
            logAndThrow("Failed to load text: " + name, e);
            return null; // unreachable, but satisfies compiler
        }
    }
}