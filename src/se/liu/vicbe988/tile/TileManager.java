package se.liu.vicbe988.tile;

import se.liu.vicbe988.background.GamePanel;
import se.liu.vicbe988.entity.Mouse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNum;
    public Mouse mouse;

    public TileManager(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
	tile = new Tile[4];	// We have 4 type of tiles (grass, water, wood and brick)
	mapTileNum = new int[gamePanel.MAX_WORLD_COL][gamePanel.MAX_WORLD_ROW];
	getTile();
	loadMap();
    }

    public void getTile() {
	try {

	    tile[0] = new Tile();
	    tile[0].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Grass.png"));

	    tile[1] = new Tile();
	    tile[1].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Brick.png"));
	    tile[1].collision = true;

	    tile[2] = new Tile();
	    tile[2].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Wood.png"));
	    tile[2].collision = true;

	    tile[3] = new Tile();
	    tile[3].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Water.png"));
	    tile[3].collision = true;

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void loadMap() {
	try {
	    InputStream inputStream = getClass().getResourceAsStream("/images/maps/map1");
	    if (inputStream == null) {
		System.err.println("Map file not found: /images/maps/map1");
		return; // if the file can't be loaded
	    }

	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	    int col = 0;
	    int row = 0;

	    String line;
	    while (row < gamePanel.MAX_WORLD_ROW && (line = bufferedReader.readLine()) != null) {
		String[] numbers = line.split(" ");
		col = 0; // Reset col for each row
		while (col < gamePanel.MAX_WORLD_COL && col < numbers.length) {
		    int number = Integer.parseInt(numbers[col]);
		    mapTileNum[col][row] = number;
		    col++;
		}
		row++;
	    }
	    bufferedReader.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }


    public void draw(Graphics2D g2) {
	int playerWorldX = gamePanel.player.mapX;
	int playerWorldY = gamePanel.player.mapY;
	int playerScreenX = gamePanel.player.screenX;
	int playerScreenY = gamePanel.player.screenY;

	for (int worldCol = 0; worldCol < gamePanel.MAX_WORLD_COL; worldCol++) {
	    for (int worldRow = 0; worldRow < gamePanel.MAX_WORLD_ROW; worldRow++) {
		int tileNum = mapTileNum[worldCol][worldRow];

		int worldX = worldCol * gamePanel.TILE_SIZE;
		int worldY = worldRow * gamePanel.TILE_SIZE;

		int screenX = worldX - playerWorldX + playerScreenX;
		int screenY = worldY - playerWorldY + playerScreenY;

		if (screenX + gamePanel.TILE_SIZE > 0 && screenX < gamePanel.SCREEN_WIDTH
		    && screenY + gamePanel.TILE_SIZE > 0 && screenY < gamePanel.SCREEN_HEIGHT) {
		    g2.drawImage(tile[tileNum].bufferedImage, screenX, screenY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
		    g2.drawImage(tile[tileNum].bufferedImage, screenX, screenY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

		}
	    }
	}
    }
}
