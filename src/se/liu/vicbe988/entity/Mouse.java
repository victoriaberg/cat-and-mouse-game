package se.liu.vicbe988.entity;

import se.liu.vicbe988.background.GamePanel;
import se.liu.vicbe988.background.IGameState;
import se.liu.vicbe988.background.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Mouse extends Entity {
    private GamePanel gamePanel;
    public BufferedImage mouse1 = null;
    private IGameState gameState = null;

    public Mouse(final GamePanel gamePanel, final KeyHandler keyHandler, int mapX, int mapY, final IGameState gameState) {
	super(mapX, mapY);
	this.gamePanel = gamePanel;
	this.gameState = gameState;
	setSolidArea(new Rectangle(8, 16, 32, 32));
	Random random = new Random();
	setSpeed(2);
	getMouseImage();
	setDefaultValues();
    }

    public void setDefaultValues() {
	setMapX(4 * GamePanel.TILE_SIZE);
	setMapY(8 * GamePanel.TILE_SIZE);
	setDirection(Direction.UP);
    }

    /**
     * Switches the mouse direction
     */
    @Override
    public void update() {
	if (gameState.hasWon()) {
	    return;
	}
	int playerCol = gamePanel.player.getMapX() / GamePanel.TILE_SIZE;
	int playerRow = gamePanel.player.getMapY() / GamePanel.TILE_SIZE;
	int mouseCol = getMapX() / GamePanel.TILE_SIZE;
	int mouseRow = getMapY() / GamePanel.TILE_SIZE;
	int currentDistance = Math.abs(mouseCol - playerCol) + Math.abs(mouseRow - playerRow);

	if (getDirection() != null) {
	    Direction originalDirection = getDirection();
	    setCollisionOn(false);
	    gamePanel.collisionControll.checkTile(this);
	    if (!isCollisionOn()) {
		int newMapX = getMapX();
		int newMapY = getMapY();
		switch (getDirection()) {
		    case UP:
			newMapY -= getSpeed();
			break;
		    case DOWN:
			newMapY += getSpeed();
			break;
		    case LEFT:
			newMapX -= getSpeed();
			break;
		    case RIGHT:
			newMapX += getSpeed();
			break;
		}
		int newCol = newMapX / GamePanel.TILE_SIZE;
		int newRow = newMapY / GamePanel.TILE_SIZE;
		int newDistance = Math.abs(newCol - playerCol) + Math.abs(newRow - playerRow);
		if (newDistance >= currentDistance) {
		    switchDirection();
		    return;
		}
	    }
	}
	java.util.List<Direction> validDirections = new java.util.ArrayList<>();
	Direction bestDirection = null;
	int bestDistanceChange = -1; // Away from player

	for (Direction dir : Direction.values()) {
	    Direction originalDirection = getDirection();
	    setDirection(dir);
	    setCollisionOn(false);
	    gamePanel.collisionControll.checkTile(this);

	    if (!isCollisionOn()) {
		validDirections.add(dir);
		int newMapX = getMapX();
		int newMapY = getMapY();
		switch (dir) {
		    case UP:
			newMapY -= getSpeed();
			break;
		    case DOWN:
			newMapY += getSpeed();
			break;
		    case LEFT:
			newMapX -= getSpeed();
			break;
		    case RIGHT:
			newMapX += getSpeed();
			break;
		}
		int newCol = newMapX / GamePanel.TILE_SIZE;
		int newRow = newMapY / GamePanel.TILE_SIZE;
		int distance = Math.abs(newCol - playerCol) + Math.abs(newRow - playerRow);
		int changeInDistance = distance - currentDistance;

		if (changeInDistance > bestDistanceChange || (bestDistanceChange <= 0 && changeInDistance >= 0)) {
		    bestDistanceChange = changeInDistance;
		    bestDirection = dir;
		}
	    }
	    setDirection(bestDirection);
	}
	if (!validDirections.isEmpty()) {
	    Direction newDirection;
	    if (bestDistanceChange > currentDistance) {
		newDirection = bestDirection;
	    } else {
		Random random = new Random();
		newDirection = validDirections.get(random.nextInt(validDirections.size()));
	    }
	    setDirection(newDirection);
	    setCollisionOn(false);
	    gamePanel.collisionControll.checkTile(this);
	    if (!isCollisionOn()) {
		switchDirection();
	    }
	}
    }

    /**
     * Draws the mouse on the screen
     */
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

    /**
     * Loads mouse image from resources
     */
    public void getMouseImage() {
	try {
	    mouse1 = loadImage("/images/mouse/Mouse.png");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}