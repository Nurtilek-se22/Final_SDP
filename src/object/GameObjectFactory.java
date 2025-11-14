package object;

public class GameObjectFactory implements ObjectFactory {
    @Override
    public SuperObject createKey() { return new OBJ_Key(); }
    @Override
    public SuperObject createDoor() { return new OBJ_Door(); }
    @Override
    public SuperObject createBoots() { return new OBJ_Boots(); }
    @Override
    public SuperObject createCheat() { return new OBJ_Cheat(); }
}
