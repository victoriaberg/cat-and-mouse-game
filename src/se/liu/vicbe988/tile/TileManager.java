package se.liu.vicbe988.tile;

import se.liu.vicbe988.background.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    public TileManager(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
	tile = new Tile[4];	// We have 4 type of tiles (grass, water, wood and brick)

	getTile();
    }

    public void getTile() {
	try {

	    tile[0] = new Tile();
	    tile[0].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Grass.png"));

	    tile[1] = new Tile();
	    tile[1].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Water.png"));

	    tile[2] = new Tile();
	    tile[2].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Wood.png"));

	    tile[3] = new Tile();
	    tile[3].bufferedImage = ImageIO.read(getClass().getResourceAsStream(
		    "/images/tiles/Brick.png"));

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void draw(Graphics2D g2) {

	g2.drawImage(tile[0].bufferedImage, 0, 0,
		     gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

	g2.drawImage(tile[1].bufferedImage, 48, 0,
		     gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

	g2.drawImage(tile[2].bufferedImage, 96, 0,
		     gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);

	g2.drawImage(tile[3].bufferedImage, 144, 0,
		     gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }
}
