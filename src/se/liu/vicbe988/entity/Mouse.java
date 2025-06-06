package se.liu.vicbe988.entity;

import se.liu.vicbe988.background.GamePanel;
import se.liu.vicbe988.background.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Mouse extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public BufferedImage mouse1;
    private static final Direction[] DIRECTIONS = Direction.values();
    private Random random;

    public Mouse(final GamePanel gamePanel, final KeyHandler keyHandler, int mapX, int mapY) {
	this.gamePanel = gamePanel;
	this.keyHandler = keyHandler;
	this.mapX = mapX;
	this.mapY = mapY;
	setSolidArea(new Rectangle(8, 16, 32, 32));
	random = new Random();
	setSpeed(2);
	getMouseImage();
    }

    @Override
    public void update() {
	if (gamePanel.hasWon) {
	    return;
	}

	// Current tile position of the mouse
	int mouseCol = getMapX() / GamePanel.TILE_SIZE;
	int mouseRow = getMapY() / GamePanel.TILE_SIZE;

	// Player's current tile position
	int playerCol = gamePanel.player.getMapX() / GamePanel.TILE_SIZE;
	int playerRow = gamePanel.player.getMapY() / GamePanel.TILE_SIZE;

	int maxDistance = 0;
	Direction bestDirection = null;
	Random random = new Random();

	java.util.List<Direction> validDirections = new java.util.ArrayList<>();	// Store valid directions

	for (Direction dir : DIRECTIONS) {
	    int newCol = mouseCol;
	    int newRow = mouseRow;
	    switch (dir) {
		case UP:
		    newRow--;
		    break;
		case DOWN:
		    newRow++;
		    break;
		case LEFT:
		    newCol--;
		    break;
		case RIGHT:
		    newCol++;
		    break;
	    }

	    // Check if tile position is in bounds and not collidable. Then update a valid direction away from the player.
	    if (newCol >= 0 && newCol < gamePanel.MAX_WORLD_COL &&
		newRow >= 0 && newRow < gamePanel.MAX_WORLD_ROW) {
		int tileNum = gamePanel.tileManager.mapTileNum[newCol][newRow];
		if (!gamePanel.tileManager.tile[tileNum].collision) {
		    validDirections.add(dir);
		    int dist = Math.abs(newCol - playerCol) + Math.abs(newRow - playerRow);
		    if (dist > maxDistance) {
			maxDistance = dist;
			bestDirection = dir;
		    }
		}
	    }
	}
	// Make sure that the mouse moves in a valid (the best) direction
	if (!validDirections.isEmpty()) {
	    setDirection(bestDirection);
	    switchDirection();
	}
    }

    @Override
    public void draw(Graphics2D g2) {
	// Calculate screen position based on map position relative to player
	int mouseScreenX = gamePanel.player.getScreenX() + (getMapX() - gamePanel.player.getMapX());
	int mouseScreenY = gamePanel.player.getScreenY() + (getMapY() - gamePanel.player.getMapY());

	if (mouseScreenX + GamePanel.TILE_SIZE > 0 && mouseScreenX < GamePanel.SCREEN_WIDTH
	    && mouseScreenY + GamePanel.TILE_SIZE > 0 && mouseScreenY < GamePanel.SCREEN_HEIGHT) {
	    g2.drawImage(mouse1, mouseScreenX, mouseScreenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
	}
    }

    public void getMouseImage() {
	try {
	    mouse1 = ImageIO.read(getClass().getResourceAsStream("/images/mouse/Mouse.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}