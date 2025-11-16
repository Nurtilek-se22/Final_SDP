package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import math.FPoint;

public class Entity {
    public SpriteAtlas sprite;
    public Frame frame;
    
    public Point pos;
    public FPoint speed;
    public int speedScalar;
    
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
