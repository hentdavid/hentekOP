package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    BufferedImage image;

    public ImageLoader(String file) {
        try {
            image = ImageIO.read(getClass().getResource("/" + file));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedImage getImage() {
        return image;
    }
}
