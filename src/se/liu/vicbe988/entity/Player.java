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

    public Player(final GamePanel gamePanel, final KeyHandler keyHandler) {
	this.gamePanel = gamePanel;
	this.keyHandler = keyHandler;
	setDefaultValues();
	getCatImage();
    }

    public void setDefaultValues() {
	x = 100;
	y = 100;
	speed = 4;
	direction = "up";
    }

    // Update method gets called 60 times/second (60 FPS) in GamePanel run function
    public void update() {
	boolean keyPressed = false;	// If key is not pressed, don't change images
	if (keyHandler.up) {
	    direction = "up";
	    y -= speed;
	    keyPressed = true;
	} else if (keyHandler.left) {
	    direction = "left";
	    x -= speed;
	    keyPressed = true;
	} else if (keyHandler.down) {
	    direction = "down";
	    y += speed;
	    keyPressed = true;
	} else if (keyHandler.right) {
	    direction = "right";
	    x += speed;
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
	switch (direction) {
	    case "up":
		if (spriteNumber == 1) {
		    bufferedImage = up1;
		} else if (spriteNumber == 2) {
		    bufferedImage = up2;
		}
		break;
	    case "left":
		if (spriteNumber == 1) {
		    bufferedImage = left1;
		} else if (spriteNumber == 2) {
		    bufferedImage = left2;
		}
		break;
	    case "down":
		if (spriteNumber == 1) {
		    bufferedImage = down1;
		} else if (spriteNumber == 2) {
		    bufferedImage = down2;
		}
		break;
	    case "right":
		if (spriteNumber == 1) {
		    bufferedImage = right1;
		} else if (spriteNumber == 2) {
		    bufferedImage = right2;
		}
		break;
	}
	g2.drawImage(bufferedImage, x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }

    public void getCatImage() {
	try {
	    up1 = ImageIO.read(getClass().getResourceAsStream("/images/Up1.png"));
	    up2 = ImageIO.read(getClass().getResourceAsStream("/images/Up2.png"));
	    left1 = ImageIO.read(getClass().getResourceAsStream("/images/Left1.png"));
	    left2 = ImageIO.read(getClass().getResourceAsStream("/images/Left2.png"));
	    down1 = ImageIO.read(getClass().getResourceAsStream("/images/Down1.png"));
	    down2 = ImageIO.read(getClass().getResourceAsStream("/images/Down2.png"));
	    right1 = ImageIO.read(getClass().getResourceAsStream("/images/Right1.png"));
	    right2 = ImageIO.read(getClass().getResourceAsStream("/images/Right2.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

