package entity;

import java.awt.image.BufferedImage;
import utils.Error;

public class Frame {
    private Frames frames;
    private int active;

    public Frame() { frames = null; active = 0; }

    public Frames getFrames() { return frames; }
    public Frame setFrames(Frames frames) {
        if (frames.imgs.length == 0) throw Error.New("Can't assign empty frames to active frame");
        this.frames = frames;
        active = 0;
        return this;
    }
    public int getActive() { return active; }
    public Frame setActive(int active) {
        active = (active % frames.imgs.length + frames.imgs.length) % frames.imgs.length;
        return this;
    }
    public Frame shift() { return setActive(active + 1); }
    public BufferedImage getActiveImage() { return frames.imgs[active]; }
}
