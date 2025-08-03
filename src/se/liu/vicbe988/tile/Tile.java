package se.liu.vicbe988.tile;

import se.liu.vicbe988.entity.Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    public BufferedImage bufferedImage = null;
    public boolean collision;

    public Tile(String imagePath, boolean collision) throws IOException {
        this.bufferedImage = Entity.loadImage(imagePath);
        this.collision = collision;
    }
}
