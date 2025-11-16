package object;

import main.GamePanel;

public class GameObjectFactory implements ObjectFactory {
    private GamePanel gp;
    public GameObjectFactory(GamePanel gp) { this.gp = gp; }

    @Override public SuperObject createKey(int x, int y)   { return new OBJ_Key(x   * gp.tileSize, y * gp.tileSize); }
    @Override public SuperObject createDoor(int x, int y)  { return new OBJ_Door(x  * gp.tileSize, y * gp.tileSize); }
    @Override public SuperObject createBoots(int x, int y) { return new OBJ_Boots(x * gp.tileSize, y * gp.tileSize); }
    @Override public SuperObject createCheat(int x, int y) { return new OBJ_Cheat(x * gp.tileSize, y * gp.tileSize); }
}
