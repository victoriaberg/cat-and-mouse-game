package se.liu.vicbe988.entity;

import se.liu.vicbe988.background.GamePanel;
import se.liu.vicbe988.background.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(final GamePanel gamePanel, final KeyHandler keyHandler) {
	this.gamePanel = gamePanel;
	this.keyHandler = keyHandler;

	screenX = gamePanel.SCREEN_WIDTH / 2 - (gamePanel.TILE_SIZE / 2);
	screenY = gamePanel.SCREEN_HEIGHT / 2 - (gamePanel.TILE_SIZE / 2);

	setDefaultValues();
	getCatImage();
    }

    public void setDefaultValues() {
	mapX = gamePanel.SCREEN_WIDTH / 2;
	mapY = gamePanel.SCREEN_HEIGHT / 2;
	speed = 4;
	direction = "up";
    }

    // Update method gets called 60 times/second (60 FPS) in GamePanel run function
    public void update() {
	boolean keyPressed = false;	// If key is not pressed, don't change images
	if (keyHandler.up) {
	    direction = "up";
	    mapY -= speed;
	    keyPressed = true;
	} else if (keyHandler.left) {
	    direction = "left";
	    mapX -= speed;
	    keyPressed = true;
	} else if (keyHandler.down) {
	    direction = "down";
	    mapY += speed;
	    keyPressed = true;
	} else if (keyHandler.right) {
	    direction = "right";
	    mapX += speed;
	    keyPressed = true;
	}

	if (keyPressed) {
	    spriteCounter++;
	    if (spriteCounter > 10) {    // Every 10 frames the cat image changes
		if (spriteNumber == 1) {
		    spriteNumber = 2;
		} else if (spriteNumber == 2) {
		    spriteNumber = 1;
		}
		spriteCounter = 0;
	    }
	}
    }

    public void draw(Graphics2D g2) {
	BufferedImage bufferedImage = null;
	boolean keyPressed = false;
	switch (direction) {
	    case "up":
		direction = "up";
		keyPressed = true;
		if (spriteNumber == 1) {
		    bufferedImage = up1;
		} else if (spriteNumber == 2) {
		    bufferedImage = up2;
		}
		break;
	    case "left":
		direction = "left";
		keyPressed = true;
		if (spriteNumber == 1) {
		    bufferedImage = left1;
		} else if (spriteNumber == 2) {
		    bufferedImage = left2;
		}
		break;
	    case "down":
		direction = "down";
		keyPressed = true;
		if (spriteNumber == 1) {
		    bufferedImage = down1;
		} else if (spriteNumber == 2) {
		    bufferedImage = down2;
		}
		break;
	    case "right":
		direction = "right";
		keyPressed = true;
		if (spriteNumber == 1) {
		    bufferedImage = right1;
		} else if (spriteNumber == 2) {
		    bufferedImage = right2;
		}
		break;
	}
	g2.drawImage(bufferedImage, mapX, mapY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }

    public void getCatImage() {
	try {
	    up1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Up1.png"));
	    up2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Up2.png"));
	    left1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Left1.png"));
	    left2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Left2.png"));
	    down1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Down1.png"));
	    down2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Down2.png"));
	    right1 = ImageIO.read(getClass().getResourceAsStream("/images/player/Right1.png"));
	    right2 = ImageIO.read(getClass().getResourceAsStream("/images/player/Right2.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

