package object;

import javax.imageio.ImageIO;
import utils.Resources;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(int x, int y) {
        super(x, y);
        name = "Door";
        image = Resources.loadImage(PATH, "door.png");
        collision = true;
    }
}
