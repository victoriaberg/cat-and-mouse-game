package se.liu.vicbe988.entity;

import se.liu.vicbe988.background.GamePanel;
import se.liu.vicbe988.background.IGameState;
import se.liu.vicbe988.background.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    /**Different images for player directions*/
    public BufferedImage up1 = null, up2 = null, left1 = null, left2 = null, down1 = null, down2 = null, right1 = null, right2 = null;
    /**Position for player on screen*/
    public final int screenX, screenY; // Cats position on the screen
    private IGameState gameState = null;

    public Player(final GamePanel gamePanel, final KeyHandler keyHandler, final IGameState gameState) {
	super();
	this.gamePanel = gamePanel;
	this.keyHandler = keyHandler;
	this.gameState = gameState;
	screenX = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE / 2);
	screenY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE / 2);
	setSolidArea(new Rectangle(8, 16, 32, 32)); // solid area for player, solid square at the bottom of the player
	setDefaultValues();
	getCatImage();
    }

    public int getScreenX() {
	return screenX;
    }

    public int getScreenY() {
	return screenY;
    }

    public void setDefaultValues() {
	setMapX((GamePanel.MAX_WORLD_COL * GamePanel.TILE_SIZE) / 2);
	setMapY((GamePanel.MAX_WORLD_ROW * GamePanel.TILE_SIZE) / 2);
	setSpeed(4);
	setDirection(Direction.UP);
    }

    /**
     * Updates the player position depending on what key is pressed
     */
    @Override
    public void update() {
	// Update method gets called 60 times/second (60 FPS) in GamePanel run function
	boolean keyPressed = false;  // If key is not pressed, don't change images
	if (gameState.hasWon()) {
	    return;
	}

	if (keyHandler.up) {
	    setDirection(Direction.UP);
	    keyPressed = true;
	} else if (keyHandler.left) {
	    setDirection(Direction.LEFT);
	    keyPressed = true;
	} else if (keyHandler.down) {
	    setDirection(Direction.DOWN);
	    keyPressed = true;
	} else if (keyHandler.right) {
	    setDirection(Direction.RIGHT);
	    keyPressed = true;
	}

	// Only proceed with movement if a key was pressed
	if (keyPressed) {
	    setCollisionOn(false);
	    gamePanel.collisionControll.checkTile(this);
	    if (!isCollisionOn()) {
		switchDirection();
	    }

	    // Update animation
	    setSpriteCounter(getSpriteCounter() + 1);
	    if (getSpriteCounter() > 10) {    // Every 10 frames the cat image changes
		if (getSpriteNumber() == 1) {
		    setSpriteNumber(2);
		} else if (getSpriteNumber() == 2) {
		    setSpriteNumber(1);
		}
		setSpriteCounter(0);
	    }
	}
    }

    /**
     * Draws the player on the screen
     */
    @Override
    public void draw(Graphics2D g2) {
	BufferedImage bufferedImage = null;
	boolean keyPressed = false;
	// Make change between player sprites
	switch (getDirection()) {
	    case Direction.UP:
		keyPressed = true;
		if (getSpriteNumber() == 1) {
		    bufferedImage = up1;
		} else if (getSpriteNumber() == 2) {
		    bufferedImage = up2;
		}
		break;
	    case Direction.LEFT:
		keyPressed = true;
		if (getSpriteNumber() == 1) {
		    bufferedImage = left1;
		} else if (getSpriteNumber() == 2) {
		    bufferedImage = left2;
		}
		break;
	    case Direction.DOWN:
		keyPressed = true;
		if (getSpriteNumber() == 1) {
		    bufferedImage = down1;
		} else if (getSpriteNumber() == 2) {
		    bufferedImage = down2;
		}
		break;
	    case Direction.RIGHT:
		keyPressed = true;
		if (getSpriteNumber() == 1) {
		    bufferedImage = right1;
		} else if (getSpriteNumber() == 2) {
		    bufferedImage = right2;
		}
		break;
	}
	g2.drawImage(bufferedImage, getScreenX(), getScreenY(), GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    /**
     * Loads the cat images from resources
     */
    public void getCatImage() {
	try {
	    up1 = loadImage("/images/player/Up1.png");
	    up2 = loadImage("/images/player/Up2.png");
	    left1 = loadImage("/images/player/Left1.png");
	    left2 = loadImage("/images/player/Left2.png");
	    down1 = loadImage("/images/player/Down1.png");
	    down2 = loadImage("/images/player/Down2.png");
	    right1 = loadImage("/images/player/Right1.png");
	    right2 = loadImage("/images/player/Right2.png");
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1); // Exit if images cannot be loaded
	}
    }
}

