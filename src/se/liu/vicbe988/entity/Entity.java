package se.liu.vicbe988.entity;

import java.awt.*;

public abstract class Entity { // Abstract parent class for characters to eg. prevent invalid object creation
    public int mapX, mapY;
    public int speed;
    public Direction direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea; // Small solid area for player to easier go in a 1x1 passage
    public boolean collisionOn = false;

    protected Entity() {
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }


    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setMapX(final int mapX) {
        this.mapX = mapX;
    }

    public void setMapY(final int mapY) {
        this.mapY = mapY;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public void setSpriteCounter(final int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public void setSpriteNumber(final int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public void setSolidArea(final Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public void setCollisionOn(final boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public abstract void update();         // Update entity state
    public abstract void draw(Graphics2D g2);

    protected void switchDirection() {
        switch (getDirection()) {
            case Direction.UP:
                setMapY(getMapY() - getSpeed());
                break;
            case Direction.DOWN:
                setMapY(getMapY() + getSpeed());
                break;
            case Direction.LEFT:
                setMapX(getMapX() - getSpeed());
                break;
            case Direction.RIGHT:
                setMapX(getMapX() + getSpeed());
                break;
        }
    }

}
