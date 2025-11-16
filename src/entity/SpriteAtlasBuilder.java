package entity;

import utils.Error;
import utils.Path;

public class SpriteAtlasBuilder {
    private Frames up, down, left, right;

    public SpriteAtlasBuilder() { up = null; down = null; left = null; right = null; }

    public SpriteAtlasBuilder setUp(Frames up)       { this.up    = up;    return this; }
    public SpriteAtlasBuilder setDown(Frames down)   { this.down  = down;  return this; }
    public SpriteAtlasBuilder setLeft(Frames left)   { this.left  = left;  return this; }
    public SpriteAtlasBuilder setRight(Frames right) { this.right = right; return this; }
    
    public SpriteAtlasBuilder setUp(Path path, String... paths)    { return setUp(new Frames(path, paths));    }
    public SpriteAtlasBuilder setDown(Path path, String... paths)  { return setDown(new Frames(path, paths));  }
    public SpriteAtlasBuilder setLeft(Path path, String... paths)  { return setLeft(new Frames(path, paths));  }
    public SpriteAtlasBuilder setRight(Path path, String... paths) { return setRight(new Frames(path, paths)); }

    public SpriteAtlas build() {
        Error.expectNotNull(up,    "up");
        Error.expectNotNull(left,  "left");
        Error.expectNotNull(right, "right");
        Error.expectNotNull(down,  "down");
        return new SpriteAtlas(up, down, left, right);
    }
}
