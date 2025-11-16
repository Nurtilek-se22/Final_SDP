package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityWorldLeft   = entity.worldX + entity.solidArea.x;
        int entityWorldRight  = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityWorldTop    = entity.worldY + entity.solidArea.y;
        int entityWorldBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol   = entityWorldLeft   / gp.tileSize;
        int entityRightCol  = entityWorldRight  / gp.tileSize;
        int entityTopRow    = entityWorldTop    / gp.tileSize;
        int entityBottomRow = entityWorldBottom / gp.tileSize;

        int tileNum1, tileNum2;
        
        if (entity.vec.x != 0) {
            int start = entity.vec.x > 0 ? entityWorldRight : entityWorldLeft;
            int entityCol = (int)(start + entity.vec.x) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
        }

        if (entity.collisionOn) return;
        if (entity.vec.y != 0) {
            int start = entity.vec.y > 0 ? entityWorldBottom : entityWorldTop;
            int entityRow = (int)(start + entity.vec.y) / gp.tileSize;
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
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                entity.solidArea.y -= entity.vec.y;
                entity.solidArea.x += entity.vec.x;
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if (gp.obj[i].collision == true){
                        entity.collisionOn = true;
                    }
                    if (player == true){
                        index=i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }

        }

        return index;
    }
}
