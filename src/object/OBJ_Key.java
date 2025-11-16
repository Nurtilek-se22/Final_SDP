package object;

import javax.imageio.ImageIO;
import utils.Resources;

public class OBJ_Key extends SuperObject{

    public OBJ_Key(){

        name = "Key";
        image = Resources.loadImage(PATH, "key.png");
    }
}
