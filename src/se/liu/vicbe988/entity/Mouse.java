package se.liu.vicbe988.entity;

import se.liu.vicbe988.background.GamePanel;
import se.liu.vicbe988.background.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Mouse extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    public BufferedImage mouse1;

    public Mouse(final GamePanel gamePanel, final KeyHandler keyHandler, int mapX, int mapY) {
	this.gamePanel = gamePanel;
	this.keyHandler = keyHandler;
	this.mapX = mapX;
	this.mapY = mapY;
	solidArea = new Rectangle(8, 16, 32, 32); // Optional: keep if collision is needed later
	getMouseImage();
    }

    public void draw(Graphics2D g2) {
	// Calculate screen position based on map position relative to player
	int mouseScreenX = gamePanel.player.screenX + (mapX - gamePanel.player.mapX);
	int mouseScreenY = gamePanel.player.screenY + (mapY - gamePanel.player.mapY);

	// Optional boundary check
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