package object;

import javax.imageio.ImageIO;
import utils.Resources;

public class OBJ_Cheat extends SuperObject {
    public OBJ_Cheat(int x, int y) {
        super(x, y);
        name = "Cheat";
        image = Resources.loadImage(PATH, "chest.png");
    }
}
