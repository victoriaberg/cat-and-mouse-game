package se.liu.vicbe988.tile;

import se.liu.vicbe988.background.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
	tile = new Tile[4];	// We have 4 type of tiles (grass, water, wood and brick)
	mapTileNum = new int[gamePanel.MAX_SCREEN_COL][gamePanel.MAX_SCREEN_ROW];
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

	    tile[2] = new Tile();
	    tile[2].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Wood.png"));

	    tile[3] = new Tile();
	    tile[3].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Water.png"));

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void loadMap() {
	try {
	    InputStream inputStream = getClass().getResourceAsStream("/images/maps/map1");
	    // Using the bufferedreader to read the context of our map
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	    int col = 0;
	    int row = 0;

	    while (col < gamePanel.MAX_SCREEN_COL && row < gamePanel.MAX_SCREEN_ROW) {
		String line = bufferedReader.readLine();

		while (col < gamePanel.MAX_SCREEN_COL) {
		    String[] numbers = line.split(" ");	// Split to get each number one by one

		    int number = Integer.parseInt(numbers[col]);
		    mapTileNum[col][row] = number;	// Store number in the map array
		    col++;
		}
		col = 0;
		row++;
	    }
	    bufferedReader.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void draw(Graphics2D g2) {
	int col = 0;
	int row = 0;
	int x = 0;
	int y = 0;

	while (col < gamePanel.MAX_SCREEN_COL && row < gamePanel.MAX_SCREEN_ROW) {

	    int tileNum = mapTileNum[col][row];	// If we get a 0 -> draw grass, 1 -> brick, etc.
	    g2.drawImage(tile[tileNum].bufferedImage, x, y,
			 gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
	    col++;
	    x += gamePanel.TILE_SIZE;

	    if (col == gamePanel.MAX_SCREEN_COL) {
		col = 0;
		x = 0;
		row++;
		y += gamePanel.TILE_SIZE;
	    }
	}
    }
}
