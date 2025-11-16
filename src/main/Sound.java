package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import utils.Resources;

public class Sound {
    Clip clip;
    URL soundURL []= new URL[30];

    public Sound() {
        soundURL[0] = Resources.loadURL("/resources/sound/BlueBoyAdventure.wav");
        soundURL[1] = Resources.loadURL("/resources/sound/coin.wav");
        soundURL[2] = Resources.loadURL("/resources/sound/powerup.wav");
        soundURL[3] = Resources.loadURL("/resources/sound/unlock.wav");
        soundURL[4] = Resources.loadURL("/resources/sound/fanfare.wav");
    }

    public void setFile(int i){
        clip = Resources.loadClip(soundURL[i]);
    }

    public void play(){

        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){

        clip.stop();
    }
}
