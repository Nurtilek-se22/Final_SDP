package main;

import java.net.URL;
import utils.Resources;

public enum SoundName {
    Music(0,   "/resources/sound/BlueBoyAdventure.wav"),
    Coin(1,    "/resources/sound/coin.wav"),
    PowerUp(2, "/resources/sound/powerup.wav"),
    Unlock(3,  "/resources/sound/unlock.wav"),
    Fanfare(4, "/resources/sound/fanfare.wav");

    public int index;
    public String path;
    private SoundName(int i, String path) { index = i; this.path = path; }

    public URL load() { return Resources.loadURL(path); }
}
