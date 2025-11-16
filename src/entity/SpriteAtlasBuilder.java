package entity;

import utils.Error;

public class SpriteAtlasBuilder {
    private Frames up, down, left, right;

    public SpriteAtlasBuilder() { up = null; down = null; left = null; right = null; }

    public SpriteAtlasBuilder setUp(Frames up)       { this.up    = up;    return this; }
    public SpriteAtlasBuilder setDown(Frames down)   { this.down  = down;  return this; }
    public SpriteAtlasBuilder setLeft(Frames left)   { this.left  = left;  return this; }
    public SpriteAtlasBuilder setRight(Frames right) { this.right = right; return this; }
    
    public SpriteAtlasBuilder setUp(String... paths)    { return setUp(new Frames(paths));    }
    public SpriteAtlasBuilder setDown(String... paths)  { return setDown(new Frames(paths));  }
    public SpriteAtlasBuilder setLeft(String... paths)  { return setLeft(new Frames(paths));  }
    public SpriteAtlasBuilder setRight(String... paths) { return setRight(new Frames(paths)); }

    public SpriteAtlas build() {
        Error.expectNotNull(up,    "up");
        Error.expectNotNull(left,  "left");
        Error.expectNotNull(right, "right");
        Error.expectNotNull(down,  "down");
        return new SpriteAtlas(up, down, left, right);
    }
}
