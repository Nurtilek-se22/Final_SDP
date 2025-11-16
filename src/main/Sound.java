package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import utils.Resources;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[SoundName.values().length];

    public Sound() {
        for (SoundName name : SoundName.values()) {
            soundURL[name.index] = name.load();
        }
    }

    public void setFile(SoundName name) {
        clip = Resources.loadClip(soundURL[name.index]);
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
