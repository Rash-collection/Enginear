/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package setups;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Detects and provides the startup root folder for the engine.
 * Handles IDE, JAR, EXE, and fallback user directories.
 */
public final class StartupPaths {

    private static final String GUEST = "Guest";
    private static String appName = GUEST;
    private static Path root;

    // Constants for runtime types
    private static final int IDE_TIME    = 0;
    private static final int JAR_PARENT  = 1;
    private static final int EXE_DOCS    = 2;

    private StartupPaths() {}

    /** Set the application name */
    public static void setAppName(String name) {
        if (name != null && !name.isBlank()) appName = name;
        else appName = GUEST;
    }

    /** Get the engine root folder (auto-detect if not set) */
    public static Path root() {
        if (root == null) root = detectRoot();
        return root;
    }

    /** Optional: runtime type detected */
    public static int runtimeType() {
        // force detection if root null
        if (root == null) detectRoot();
        return detectedType;
    }

    // internally track the runtime type
    private static int detectedType = IDE_TIME;

    /** Detect the real root folder based on runtime */
    private static Path detectRoot() {
        Path parentFolder;
        String rootFolderName;

        try {
            // Detect the location of the running code
            Path location = Path.of(
                    StartupPaths.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).toAbsolutePath();

            if (Files.isRegularFile(location)) {
                // JAR or EXE
                parentFolder = location.getParent();
                if (location.toString().endsWith(".jar")) {
                    rootFolderName = appName;      // JAR: root = <parent>/appName
                    detectedType = JAR_PARENT;
                    System.out.println("Detected JAR runtime");
                } else {
                    rootFolderName = "source";     // EXE: root = <parent>/source
                    detectedType = JAR_PARENT;     // treat EXE same as JAR parent
                    System.out.println("Detected EXE runtime");
                }
            } else {
                // IDE / dev time
                parentFolder = Path.of("").toAbsolutePath(); // project root
                rootFolderName = "results";                  // IDE: root = <project>/results
                detectedType = IDE_TIME;
                System.out.println("Detected IDE runtime");
            }

        } catch (URISyntaxException e) {
            // Fallback to user documents if detection fails
            parentFolder = Path.of(System.getProperty("user.home"), "Documents");
            rootFolderName = "Collection/" + appName;
            detectedType = EXE_DOCS;
            System.out.println("Fallback: user documents");
        }

        // Combine parent folder + root folder name
        Path realRoot = parentFolder.resolve(rootFolderName);

        // Ensure the root folder exists
        try {
            Files.createDirectories(realRoot);
        } catch (IOException ioEx) {
            throw new RuntimeException("Failed to create root folder: " + realRoot, ioEx);
        }

        return realRoot;
    }
}