/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package setups;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author rash4
 */
public final class StartupStructure {
    private static final Map<String, Path> STRUCTURE = new LinkedHashMap<>();
    static {
        // general defaults (NOT sacred)
        STRUCTURE.put("images", Path.of("media/images"));
        STRUCTURE.put("audios", Path.of("media/audios"));
        STRUCTURE.put("videos", Path.of("media/videos"));
        STRUCTURE.put("texts",  Path.of("texts"));
        STRUCTURE.put("items",  Path.of("items"));
        STRUCTURE.put("logs",   Path.of("logs"));
        STRUCTURE.put("config", Path.of("config"));
    }
    private StartupStructure() {}
    public static void createAllDirs() {
        Path root = StartupPaths.root(); // get the detected or set root
        for (Path rel : STRUCTURE.values()) {
            Path abs = root.resolve(rel); // full absolute path
            try {
                Files.createDirectories(abs); // creates the folder and any missing parents
            } catch (IOException e) {
                throw new RuntimeException("Failed to create folder: " + abs, e);
            }
        }
    }
    public static Path resolve(String key) {
        Path rel = STRUCTURE.get(key);
        if (rel == null) {
            throw new IllegalArgumentException(
                "Unknown structure key: " + key
            );
        }
        return StartupPaths.root().resolve(rel);
    }
    public static void register(String key, Path relativePath) {
        if (key == null || key.isBlank())
            throw new IllegalArgumentException("Structure key is empty");
        STRUCTURE.put(key, relativePath);
    }
    public static Map<String, Path> snapshot() {
        return Map.copyOf(STRUCTURE);
    }
}