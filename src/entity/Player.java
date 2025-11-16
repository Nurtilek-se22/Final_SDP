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
import java.util.ArrayList;
import utils.Resources;
import utils.Path;
import utils.Timer;
import main.SoundName;
import math.FPoint;
import event.GameEvent;
import observer.EventObserver;
import observer.ConsoleObserver;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    ArrayList<EventObserver> observers = new ArrayList<>();
    
    public static final Path PATH = new Path(Resources.PATH, "player/");
    Timer timerSprite = new Timer(0.2);

    public void addEventListener(EventObserver observer) {
        observers.add(observer);
    }

    public void notify(GameEvent event) {
        for (EventObserver observer : observers) {
            observer.handleEvent(event);
        }
    }

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.attackStrategy = new RangedAttack();

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        pos = new Point(gp.tileSize * 23, gp.tileSize * 21);
        speedScalar = 4;
        speed = new FPoint();

        frame = new Frame();
        sprite = new SpriteAtlasBuilder()
                    .setUp(PATH, "boy_up_1.png", "boy_up_2.png")
                    .setDown(PATH, "boy_down_1.png", "boy_down_2.png")
                    .setLeft(PATH, "boy_left_1.png", "boy_left_2.png")
                    .setRight(PATH, "boy_right_1.png", "boy_right_2.png")
                    .build()
                    .setActiveFrame(frame, Direction.Down);
        setAttackStrategy(new MagicAttack());

        addEventListener(new ConsoleObserver());
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

        speed.setLocation(0, 0);
        if (input.isUp() == true){
            speed.y -= 1;
            dir = Direction.Up;
        }
        if(keyH.downPressed == true){
            speed.y += 1;
            dir = Direction.Down;
        }
        if(keyH.leftPressed == true){
            speed.x -= 1;
            dir = Direction.Left;
        }
        if(keyH.rightPressed == true){
            speed.x += 1;
            dir = Direction.Right;
        }
        if (dir == null) return;
        speed.norm().scale(speedScalar);
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
            pos.x += speed.x;
            pos.y += speed.y;
        }

        if(timerSprite.isFinished()) {
            frame.shift();
            timerSprite.reset();
        }
    }

    public void pickUpObject(int i){
        if (i == -1) return;
        String objectName = gp.obj[i].name;

        switch (objectName) {
            case "Key":
                gp.playSE(SoundName.Coin);
                hasKey++;
                gp.obj[i] = null;
                gp.ui.showMessage("You got a key!");
                notify(GameEvent.PickUpKey);
                break;
            case "Door":
                if (hasKey > 0) {
                    notify(GameEvent.OpenDoor);
                    gp.playSE(SoundName.Unlock);
                    gp.obj[i] = null;
                    hasKey--;
                    gp.ui.showMessage("You opened the door!");
                } else {
                    gp.ui.showMessage("You need a key!");
                }
                break;
            case "Boots":
                notify(GameEvent.PickUpBoots);
                gp.playSE(SoundName.PowerUp);
                speedScalar += 2;
                gp.obj[i] = null;
                gp.ui.showMessage("Speed up!");
                break;
            case "Cheat":
                if (!gp.ui.gameFinished) notify(GameEvent.GameFinished);
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(SoundName.Fanfare);
                break;

        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(frame.getActiveImage(),screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
