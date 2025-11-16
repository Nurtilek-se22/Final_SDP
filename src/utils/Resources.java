package utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class Resources {
    private Resources() {}
    private static final String RESOURCES_PATH       = Resources.class.getResource("/").getPath();
    private static final int    RESOURCES_PATH_LEN   = RESOURCES_PATH.length();
    private static final String IMG_PLACEHOLDER_PATH = "/resources/placeholder/missing.png";
    public static final Path PATH = new Path("/resources/");

    public static InputStream loadInputStream(String path) {
        return Resources.class.getResourceAsStream(path);
    }
    public static InputStream loadInputStream(Path base, String path) { return loadInputStream(base.getPath(path)); }
    private static void logSuccess(String path) {
        Logger.info.printfn("Successfully loaded \"%s\"!", path);
    }
    private static void logSuccess(String desc, String path) {
        Logger.info.printfn("Successfully loaded %s from \"%s\"!", desc, path);
    }
    private static void logFail(Throwable e, String path) {
        Logger.err.eprintfn("Couldn't load \"%s\".", path);
        e.printStackTrace();
    }
    private static void logFail(Throwable e, String path, String fmt, Object... args) {
        Logger.err.eprintfn("Couldn't load \"%s\": " + fmt, path, args);
        e.printStackTrace();
    }
    private static void logFail(Throwable e, String path, String msg) {
        Logger.err.eprintfn("Couldn't load \"%s\": %s", path, msg);
        e.printStackTrace();
    }
    private static void logFail(Throwable e, String path, Object obj) {
        Logger.err.eprintfn("Couldn't load \"%s\": %s", path, obj.toString());
        e.printStackTrace();
    }
    private static void logNotFound(Throwable e, String path) {
        Logger.err.eprintfn("Couldn't load \"%s\".", path);
        Throwable t = Error.New(e, "file not found");
        Error.dropFrame(t).printStackTrace();
    }
    
    public static URL loadURL(String path) {
        URL result = Resources.class.getResource(path);
        logSuccess(path);
        return result;
    }
    public static URL loadURL(Path base, String path) { return loadURL(base.getPath(path)); }

    public static Clip loadClip(URL url) {
        AudioInputStream audio = null;
        String path = url.getPath().substring(RESOURCES_PATH_LEN - 1);
        try {
            audio = AudioSystem.getAudioInputStream(url);
        } catch (UnsupportedAudioFileException e) {
            logFail(e, path, "Unknown audio format");
        } catch (IOException e) {
            logFail(e, path);
        }
        if (audio == null) return null;
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(audio);
            logSuccess("clip", path);
        } catch (Exception e) {
            clip = null;
            logFail(e, path, "clip failed");
        }
        return clip;
    }

    public static BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(loadInputStream(path));
            logSuccess(path);
        } catch (IllegalArgumentException e) {
            logNotFound(e, path);
        } catch (Exception e) {
            try {
                logFail(e, path);
                img = ImageIO.read(loadInputStream(IMG_PLACEHOLDER_PATH));
                Logger.warn.printfn("Loaded placeholder for \"%s\"", path);
            } catch (Exception ep) {
                throw Error.New(ep, "Couldn't load placeholder \"%s\"", IMG_PLACEHOLDER_PATH);
            }
        }
        return img;
    }
    public static BufferedImage loadImage(Path base, String path) { return loadImage(base.getPath(path)); }
}
