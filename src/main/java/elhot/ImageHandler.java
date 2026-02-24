/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elhot;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import setups.AbsHandler;
import setups.StartupPaths;
import setups.StartupThrowable;

/**
 *
 * @author rash4
 */
public final class ImageHandler extends AbsHandler<BufferedImage> {
    public ImageHandler(Path root) { super(root); }
    public ImageHandler(){super(StartupPaths.root());}
    @Override public void save(BufferedImage image, String name) throws StartupThrowable {
        Path dir = root.resolve("images");
        ensureDir(dir);
        Path file = dir.resolve(name);
        try {
            ImageIO.write(image, "png", file.toFile());
        } catch (IOException e) {
            logAndThrow("Failed to save image: " + name, e);
        }
    }
    @Override public BufferedImage load(String name) throws StartupThrowable {
        Path file = root.resolve("images").resolve(name);
        try {
            return ImageIO.read(file.toFile());
        } catch (IOException e) {
            logAndThrow("Failed to load image: " + name, e);
            return null;
        }
    }
}