package object;

import javax.imageio.ImageIO;

public class OBJ_Cheat extends SuperObject
{
    public OBJ_Cheat() {
        name = "Cheat";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
