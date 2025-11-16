package object;

import javax.imageio.ImageIO;
import utils.Resources;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(){

        name = "Door";
        image = Resources.loadImage(PATH, "door.png");
        collision = true;
    }
}
