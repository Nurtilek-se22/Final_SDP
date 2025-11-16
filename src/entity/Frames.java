package entity;

import java.awt.image.BufferedImage;
import utils.Resources;
import utils.Path;

public class Frames {
    public BufferedImage[] imgs;

    public Frames(Path path, String... paths) {
        imgs = new BufferedImage[paths.length];

        for (int i = 0; i < paths.length; ++i) {
            imgs[i] = Resources.loadImage(path.getPath(paths[i]));
        }
    }
}
