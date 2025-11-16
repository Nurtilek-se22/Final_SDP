package main;

import java.net.URL;
import utils.Resources;
import utils.Path;

public enum SoundName { 
    Music(0,   "BlueBoyAdventure.wav"),
    Coin(1,    "coin.wav"),
    PowerUp(2, "powerup.wav"),
    Unlock(3,  "unlock.wav"),
    Fanfare(4, "fanfare.wav");

    public int index;
    public String path;
    private SoundName(int i, String path) { index = i; this.path = path; }

    public static final Path PATH = new Path(Resources.PATH, "sound/");
    public URL load() { return Resources.loadURL(PATH, path); }
}
