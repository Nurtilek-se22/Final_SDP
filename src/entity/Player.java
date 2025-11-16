package entity;

import input.InputAdapter;
import input.KeyboardAdapter;
import main.GamePanel;
import main.KeyHandler;
import patterns.strategy.AttackStrategy;
import patterns.strategy.MagicAttack;
import patterns.strategy.MeleeAttack;
import patterns.strategy.RangedAttack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import utils.Resources;
import math.FPoint;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.attackStrategy = new RangedAttack();

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        vec = new FPoint();

        frame = new Frame();
        sprite = new SpriteAtlasBuilder()
                    .setUp("/resources/player/boy_up_1.png", "/resources/player/boy_up_2.png")
                    .setDown("/resources/player/boy_down_1.png", "/resources/player/boy_down_2.png")
                    .setLeft("/resources/player/boy_left_1.png", "/resources/player/boy_left_2.png")
                    .setRight("/resources/player/boy_right_1.png", "/resources/player/boy_right_2.png")
                    .build()
                    .setActiveFrame(frame, Direction.Down);
        setAttackStrategy(new MagicAttack());
    }

    private AttackStrategy attackStrategy;

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    public void performAttack() {
        attackStrategy.attack();
        gp.ui.showMessage("Атака выполнена: " + attackStrategy.getClass().getSimpleName());
    }

    public void update(){
        InputAdapter input = new KeyboardAdapter(keyH);
        Direction dir = null;

        vec.setLocation(0, 0);
        if (input.isUp() == true){
            vec.y -= 1;
            dir = Direction.Up;
        }
        if(keyH.downPressed == true){
            vec.y += 1;
            dir = Direction.Down;
        }
        if(keyH.leftPressed == true){
            vec.x -= 1;
            dir = Direction.Left;
        }
        if(keyH.rightPressed == true){
            vec.x += 1;
            dir = Direction.Right;
        }
        if (dir == null) return;
        vec.norm().scale(speed);
        sprite.setActiveFrame(frame, dir);

        if (keyH.attackPressed) {
            performAttack();
        }

        //CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // CHECK OBJECT COLLISION
        int objIndex = gp.cChecker.checkObject(this,true);
        pickUpObject(objIndex);

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (collisionOn == false) {
            worldX += vec.x;
            worldY += vec.y;
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            frame.shift();
            spriteCounter = 0;
        }
    }

    public void pickUpObject(int i){
        if (i == -1) return;
        String objectName = gp.obj[i].name;

        switch (objectName){
            case "Key":
                gp.playSE(1);
                hasKey++;
                gp.obj[i] = null;
                gp.ui.showMessage("You got a key!");
                break;
            case "Door":
                gp.playSE(3);
                if(hasKey >0){
                    gp.obj[i] = null;
                    hasKey--;
                    gp.ui.showMessage("You opened the door!");
                }
                else{
                    gp.ui.showMessage("You need a key!");
                }
                break;
            case "Boots":
                gp.playSE(2);
                speed += 2;
                gp.obj[i] = null;
                gp.ui.showMessage("Speed up!");
                break;
            case "Cheat":
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(4);
                break;

        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(frame.getActiveImage(),screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
