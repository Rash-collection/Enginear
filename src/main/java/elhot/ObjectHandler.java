/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elhot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import setups.AbsHandler;
import setups.StartupThrowable;

/**
 *
 * @author rash4
 */
public final class ObjectHandler extends AbsHandler<Object> {
    public ObjectHandler(Path root) { super(root); }
    @Override public void save(Object obj, String name) throws StartupThrowable {
        Path dir = root.resolve("objects");
        ensureDir(dir);
        Path file = dir.resolve(name);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            logAndThrow("Failed to save object: " + name, e);
        }
    }
    @Override public Object load(String name) throws StartupThrowable {
        Path file = root.resolve("objects").resolve(name);
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logAndThrow("Failed to load object: " + name, e);
            return null;
        }
    }
}