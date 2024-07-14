package se.liu.vicbe988.background;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    static final int STANDARD_TILE_SIZE = 16; // 16x16 standard tile
    static final int SCALE = 3; // Original scale
    static final int TILE_SIZE = STANDARD_TILE_SIZE * SCALE; // 48x48
    static final int MAX_SCREEN_COL = 16;
    static final int MAX_SCREEN_ROW = 12;
    static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // Pixels

    public GamePanel() {
	// Set size of JPanel class
	this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	this.setBackground(Color.GREEN);
	this.setDoubleBuffered(true); // can improve game rendering performance
	this.addKeyListener(keyHandler); // Listenes for pressed key
	this.setFocusable(true); // Focused to recieve key
    }

    public void setGameThread() {
	gameThread = new Thread(this);
	gameThread.start(); // Automatically calls run method
    }

    @Override public void run() {
	double drawInterval = (double) 1000000000 / FPS; // Repaints screen ever ~0.0166 seconds
	double delta = 0;
	long lastTime = System.nanoTime();
//	long timer = 0;
//	int drawFPS = 0;

	while(gameThread != null) {

	    long currentTime = System.nanoTime();
	    delta += (currentTime - lastTime) / drawInterval;
//	    timer += (currentTime - lastTime);
	    lastTime = currentTime;

	    if(delta >= 1) {
		update();
		repaint();
		delta--;
//		drawFPS++;
	    }

//	    if(timer >= 1000000000) {	// Bara för att visa fps att det fungerar
//		System.out.println("FPS: " + drawFPS);
//		drawFPS = 0;
//		timer = 0;
//	    }
	}
    }

    public void update() {
	if(keyHandler.up) { // If true make player go up
	    playerY -= playerSpeed;
	}
	if(keyHandler.left) {
	    playerX -= playerSpeed;
	}
	if(keyHandler.down) {
	    playerY += playerSpeed;
	}
	if(keyHandler.right) {
	    playerX += playerSpeed;
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g; // Change g to 2D
	g2.setColor(Color.BLUE);
	g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
	g2.dispose();
    }
}
