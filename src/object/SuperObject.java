package object;

import main.GamePanel;
import utils.Resources;
import utils.Path;

import java.awt.*;
import java.awt.image.BufferedImage;
    

public class SuperObject {
    public static final Path PATH = new Path(Resources.PATH, "objects/");

    public BufferedImage image;
    public String name;
    public boolean collision =  false;
    public Point pos;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public SuperObject(int x, int y) { pos = new Point(x, y); }

    public void draw(Graphics g2, GamePanel gp) {
        int screenX = pos.x - gp.player.pos.x + gp.player.screenX;
        int screenY = pos.y - gp.player.pos.y + gp.player.screenY;

        if(pos.x + gp.tileSize > gp.player.pos.x - gp.player.screenX &&
                pos.x - gp.tileSize < gp.player.pos.x + gp.player.screenX &&
                pos.y + gp.tileSize > gp.player.pos.y - gp.player.screenY &&
                pos.y - gp.tileSize < gp.player.pos.y + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }

    }
}
