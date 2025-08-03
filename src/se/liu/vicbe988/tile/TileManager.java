package se.liu.vicbe988.tile;

import se.liu.vicbe988.background.GamePanel;
import se.liu.vicbe988.entity.Mouse;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GamePanel gamePanel;
    /**Array of tiles*/
    public Tile[] tile;
    /**2d array to store tile numbers*/
    public int[][] mapTileNum;
    public Mouse mouse = null;

    public TileManager(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
	tile = new Tile[4];	// We have 4 type of tiles (grass, water, wood and brick)
	mapTileNum = new int[GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];
	getTile();
	loadMap();
    }

    /**
     * Loads the tile images from resources
     */
    public void getTile() {
	try {
	    tile[0] = new Tile("/images/tiles/grass.png", false);
	    tile[1] = new Tile("/images/tiles/Brick.png", true);
	    tile[2] = new Tile("/images/tiles/Wood.png", true);
	    tile[3] = new Tile("/images/tiles/Water.png", true);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
    }

    /**
     * Loads the map from a text file (2D array)
     */
    public void loadMap() {
	try {
	    InputStream inputStream = getClass().getResourceAsStream("/images/maps/map0");
	    if (inputStream == null) {
		System.err.println("Map file not found: /images/maps/map1");
		return; // if the file can't be loaded
	    }

	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	    int col = 0;
	    int row = 0;

	    String line;
	    while (row < GamePanel.MAX_WORLD_ROW && (line = bufferedReader.readLine()) != null) {
		String[] numbers = line.split(" ");
		col = 0; // Reset col for each row
		while (col < GamePanel.MAX_WORLD_COL && col < numbers.length) {
		    int number = Integer.parseInt(numbers[col]);
		    mapTileNum[col][row] = number;
		    col++;
		}
		row++;
	    }
	    bufferedReader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Draws the tiles on the screen
     */
    public void draw(Graphics2D g2) {
	int playerWorldX = gamePanel.player.mapX;
	int playerWorldY = gamePanel.player.mapY;
	int playerScreenX = gamePanel.player.screenX;
	int playerScreenY = gamePanel.player.screenY;

	for (int worldCol = 0; worldCol < GamePanel.MAX_WORLD_COL; worldCol++) {
	    for (int worldRow = 0; worldRow < GamePanel.MAX_WORLD_ROW; worldRow++) {
		int tileNum = mapTileNum[worldCol][worldRow];

		int worldX = worldCol * GamePanel.TILE_SIZE;
		int worldY = worldRow * GamePanel.TILE_SIZE;

		int screenX = worldX - playerWorldX + playerScreenX;
		int screenY = worldY - playerWorldY + playerScreenY;

		if (screenX + GamePanel.TILE_SIZE > 0 && screenX < GamePanel.SCREEN_WIDTH
		    && screenY + GamePanel.TILE_SIZE > 0 && screenY < GamePanel.SCREEN_HEIGHT) {
		    g2.drawImage(tile[tileNum].bufferedImage, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
		}
	    }
	}
    }
}
