package object;

import javax.imageio.ImageIO;
import utils.Resources;

public class OBJ_Boots extends SuperObject{
    public OBJ_Boots(){
        name = "Boots";
        image = Resources.loadImage("/resources/objects/boots.png");
    }
}
