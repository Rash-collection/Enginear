/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package elhot;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * <h2>Images Paint-Handler</h2>
 * <p>Utility static class with static methods for the sole role of handling images.</p>
 * paint-on-image .. pixelate-on-image .. read-image-pixels ..etc.
 * 
 * @author rash4
 */
public final class MageCons{
    private MageCons(){}
    public static int[][] toPixels(BufferedImage img){
        if(img == null)throw new IllegalArgumentException(
                "\nNPE -> BufferedImage img is null.\n");
        if(!(img.getRaster().getDataBuffer() instanceof DataBufferInt))
            throw new IllegalArgumentException(
                    "Image must have an INT-based raster (e.g., TYPE_INT_ARGB).");
        final int w = img.getWidth(), h = img.getHeight(),
                grid[][] = new int[h][w], 
                pixels[] = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        for(int row = 0; row < h; row++){ // this avoid nested for-loops -_-
            System.arraycopy(pixels, row * w, grid[row], 0, w);
        }
        return grid;
    }
    /**
     * <h2>Draw onto an Image pixel by pixel</h2>
     * <p><b>note:</b> that jagged arrays might throw exception.</p>
     * @param grid  The 2D array that holds the colors values for each pixel on the image-grid
     * @return      BufferedImage with the size of the 2D array
     */
    @SuppressWarnings ("unused")
    public static BufferedImage toImage(int[][] grid){
        final int h = grid.length, w = grid[0].length;
        BufferedImage img = new BufferedImage(w, h, 2);
        // actually the remote/pointer is read from within the array
        // and that's the point XD
        @SuppressWarnings("MismatchedReadAndWriteOfArray") // actually nothing ;)
        int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        for (int y = 0; y < h; y++) { // avoids nested for-loops
            System.arraycopy(grid[y], 0, pixels, y * w, w);
        }
        return img;
    }
    /**
     * <h2>Draw onto an Image pixel by pixel</h2>
     * <p><b>note:</b> that jagged arrays might throw exception.</p>
     * @param grid  The 2D array that holds the colors values for each pixel on the image-grid
     * @return      BufferedImage with the size of the 2D array
     */
    @SuppressWarnings ("unused")
    public static BufferedImage toImage(Pixelator pixelated){
        return toImage(pixelated.pixelate());
    }
    public static BufferedImage printOn(int w, int h, Painter printIt){
        if(w <= 0 || h <= 0) throw new IllegalArgumentException("Taboo zero/negative size.");
        final var img = new BufferedImage(w, h, 2);
        final var grr = img.createGraphics();
        printIt.paint(grr);
        grr.dispose();
        return img;
    }
    /**<h3>Normalize image.</h3><p>A helper method for the sake of reading image into <b>RGBA</b> Integer.</p>*/
    public static BufferedImage asIntArgb(BufferedImage src) {
        if (src.getType() == BufferedImage.TYPE_INT_ARGB)
            return src;
        BufferedImage converted = new BufferedImage(
                src.getWidth(),
                src.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        converted.getGraphics().drawImage(src, 0, 0, null);
        return converted;
    }
}