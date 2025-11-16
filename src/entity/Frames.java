package entity;

import java.awt.image.BufferedImage;
import utils.Resources;

public class Frames {
    public BufferedImage[] imgs;

    public Frames(String... paths) {
        imgs = new BufferedImage[paths.length];

        for (int i = 0; i < paths.length; ++i) {
            imgs[i] = Resources.loadImage(paths[i]);
        }
    }
}
