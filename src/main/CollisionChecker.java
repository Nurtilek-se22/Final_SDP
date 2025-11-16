package main;

import java.awt.Rectangle;
import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){ this.gp = gp; }

    public void checkTile(Entity entity){
        int entityWorldLeft   = entity.pos.x + entity.solidArea.x;
        int entityWorldRight  = entity.pos.x + entity.solidArea.x + entity.solidArea.width;
        int entityWorldTop    = entity.pos.y + entity.solidArea.y;
        int entityWorldBottom = entity.pos.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol   = entityWorldLeft   / gp.tileSize;
        int entityRightCol  = entityWorldRight  / gp.tileSize;
        int entityTopRow    = entityWorldTop    / gp.tileSize;
        int entityBottomRow = entityWorldBottom / gp.tileSize;

        int tileNum1, tileNum2;
        
        if (entity.speed.x != 0) {
            int start = entity.speed.x > 0 ? entityWorldRight : entityWorldLeft;
            int entityCol = (int)(start + entity.speed.x) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
        }

        if (entity.collisionOn) return;
        if (entity.speed.y != 0) {
            int start = entity.speed.y > 0 ? entityWorldBottom : entityWorldTop;
            int entityRow = (int)(start + entity.speed.y) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
        }
    }
    public int checkObject(Entity entity,boolean player){
        int index = -1;

        for(int i = 0; i <gp.obj.length; i++){
            if(gp.obj[i] !=null){
                //Get entity's solid area position
                Rectangle entityArea = new Rectangle(entity.solidArea);
                entityArea.x += entity.pos.x + entity.speed.x;
                entityArea.y += entity.pos.y - entity.speed.y;
                // Get the object's solid area position
                Rectangle objArea = new Rectangle(gp.obj[i].solidArea);
                objArea.x += gp.obj[i].pos.x;
                objArea.y += gp.obj[i].pos.y;

                if(entityArea.intersects(objArea)){
                    if (gp.obj[i].collision == true){
                        entity.collisionOn = true;
                    }
                    if (player == true){
                        index=i;
                    }
                }
            }

        }

        return index;
    }
}
