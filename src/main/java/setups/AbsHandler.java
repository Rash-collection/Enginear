/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package setups;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author rash4
 */
public abstract class AbsHandler<T> {
    protected Path root;
    public AbsHandler(Path root) {
        this.root = root;
    }
    public abstract void save(T data, String name) throws StartupThrowable;
    public abstract T load(String name) throws StartupThrowable;
    protected void ensureDir(Path dir) throws StartupThrowable {
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new StartupThrowable("Failed to create dir: " + dir, e);
        }
    }
    protected void logAndThrow(String msg, Throwable t) throws StartupThrowable {
        StartupLog.log(msg);
        throw new StartupThrowable(msg, t);
    }
}