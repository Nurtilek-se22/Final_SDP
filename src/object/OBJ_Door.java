package object;

import javax.imageio.ImageIO;
import utils.Resources;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(){

        name = "Door";
        image = Resources.loadImage("/resources/objects/door.png");
        collision = true;
    }
}
