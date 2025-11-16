package main;

import object.ObjectFactory;
import object.GameObjectFactory;

public class AssetSetter {
    ObjectFactory factory;
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
        this.factory = new GameObjectFactory(gp);
    }

    public void setObject(){
        gp.obj[0] = factory.createKey(23, 7);
        gp.obj[1] = factory.createKey(23, 40);
        gp.obj[2] = factory.createKey(38, 8);
        gp.obj[3] = factory.createDoor(10, 11);
        gp.obj[4] = factory.createDoor(8, 28);
        gp.obj[5] = factory.createDoor(12, 22);
        gp.obj[6] = factory.createCheat(10, 7);
        gp.obj[7] = factory.createBoots(37, 42);
    }
}
