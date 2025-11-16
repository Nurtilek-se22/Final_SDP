package object;

import javax.imageio.ImageIO;
import utils.Resources;

public class OBJ_Boots extends SuperObject{
    public OBJ_Boots(int x, int y) {
        super(x, y);
        name = "Boots";
        image = Resources.loadImage(PATH, "boots.png");
    }
}
