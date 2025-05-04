package se.liu.vicbe988.entity;

import java.awt.Rectangle;

public class Entity { // Parent class for our player (or other characters)
    public int mapX, mapY;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea; // Small solid area for player to easier go in a 1x1 passage
    public boolean collisionOn = false;
}
