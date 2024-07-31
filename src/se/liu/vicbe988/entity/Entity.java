package se.liu.vicbe988.entity;

import java.awt.image.BufferedImage;

public class Entity { // Parent class for our player (or other characters)
	public int mapX, mapY;
	public int speed;
	// Buffered image = Image with an accessible buffer of image data.
	public BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
	public String direction;

	public int spriteCounter = 0;
	public int spriteNumber = 1;
}
