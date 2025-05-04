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
    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};
    private Random random;
    private int speed = 2;
    private int directionCounter = 0;
    private int directionDuration = 30;

    public Mouse(final GamePanel gamePanel, final KeyHandler keyHandler, int mapX, int mapY) {
	this.gamePanel = gamePanel;
	this.keyHandler = keyHandler;
	this.mapX = mapX;
	this.mapY = mapY;
	solidArea = new Rectangle(8, 16, 32, 32);
	random = new Random();
	getMouseImage();
    }

    public void update() {
	if (gamePanel.hasWon) {
	    return;
	}

	// Current tile position of the mouse
	int mouseCol = mapX / gamePanel.TILE_SIZE;
	int mouseRow = mapY / gamePanel.TILE_SIZE;

	// Player's current tile position
	int playerCol = gamePanel.player.mapX / gamePanel.TILE_SIZE;
	int playerRow = gamePanel.player.mapY / gamePanel.TILE_SIZE;

	String[] possibleDirections = {"up", "down", "left", "right"};
	int maxDistance = -1;
	String bestDirection = null;
	Random random = new Random();

	java.util.List<String> validDirections = new java.util.ArrayList<>();	// Store valid directions

	for (String dir : possibleDirections) {
	    int newCol = mouseCol;
	    int newRow = mouseRow;
	    switch (dir) {
		case "up":
		    newRow--;
		    break;
		case "down":
		    newRow++;
		    break;
		case "left":
		    newCol--;
		    break;
		case "right":
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
	    if (random.nextDouble() < 0.1) { // 10% chance to pick a random direction for more randomness
		bestDirection = validDirections.get(random.nextInt(validDirections.size()));
	    }

	    direction = bestDirection;
	    switch (direction) {
		case "up":
		    mapY -= speed;
		    break;
		case "down":
		    mapY += speed;
		    break;
		case "left":
		    mapX -= speed;
		    break;
		case "right":
		    mapX += speed;
		    break;
	    }
	}
    }

    public void draw(Graphics2D g2) {
	// Calculate screen position based on map position relative to player
	int mouseScreenX = gamePanel.player.screenX + (mapX - gamePanel.player.mapX);
	int mouseScreenY = gamePanel.player.screenY + (mapY - gamePanel.player.mapY);

	if (mouseScreenX + gamePanel.TILE_SIZE > 0 && mouseScreenX < gamePanel.SCREEN_WIDTH
	    && mouseScreenY + gamePanel.TILE_SIZE > 0 && mouseScreenY < gamePanel.SCREEN_HEIGHT) {
	    g2.drawImage(mouse1, mouseScreenX, mouseScreenY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
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