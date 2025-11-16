package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import math.FPoint;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public SpriteAtlas sprite;
    public FPoint vec;
    public Frame frame;
    public int spriteCounter = 0;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
