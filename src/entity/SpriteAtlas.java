package entity;

public class SpriteAtlas {
    public Frames[] frames;

    public SpriteAtlas(Frames up, Frames down, Frames left, Frames right) {
        frames = new Frames[Direction.values().length];
        frames[Direction.Up.value]    = up;
        frames[Direction.Down.value]  = down;
        frames[Direction.Left.value]  = left;
        frames[Direction.Right.value] = right;
    }
    public Frames getFrames(Direction dir) { return frames[dir.value]; }
    public SpriteAtlas setActiveFrame(Frame frame, Direction dir) {
        frame.setFrames(frames[dir.value]);
        return this;
    }
}
