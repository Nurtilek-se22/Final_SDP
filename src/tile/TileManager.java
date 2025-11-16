package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import utils.Resources;
import utils.Path;

public class TileManager {
    public static final Path TILES_PATH = new Path(Resources.PATH, "tiles/");
    public static final Path MAPS_PATH = new Path(Resources.PATH, "maps/");

    GamePanel gp;
    public Tile [] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("world01.txt");
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].image = Resources.loadImage(TILES_PATH, "grass.png");

        tile[1] = new Tile();
        tile[1].image = Resources.loadImage(TILES_PATH, "wall.png");
        tile[1].collision = true;

        tile[2] = new Tile();
        tile[2].image = Resources.loadImage(TILES_PATH, "water.png");
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].image = Resources.loadImage(TILES_PATH, "earth.png");

        tile[4] = new Tile();
        tile[4].image = Resources.loadImage(TILES_PATH, "tree.png");
        tile[4].collision = true;

        tile[5] = new Tile();
        tile[5].image = Resources.loadImage(TILES_PATH, "sand.png");
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(MAPS_PATH.getPath(filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();
                if(line == null) break;

                String numbers[] = line.trim().split("\\s+");

                while (col < gp.maxWorldCol){
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;
        int x = 0;
        int y = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.pos.x + gp.player.screenX;
            int screenY = worldY - gp.player.pos.y + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.pos.x - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.pos.x + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.pos.y - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.pos.y + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
