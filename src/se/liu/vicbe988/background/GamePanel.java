package se.liu.vicbe988.background;

import se.liu.vicbe988.entity.Player;
import se.liu.vicbe988.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    static final int STANDARD_TILE_SIZE = 16; // 16x16 standard tile
    static final int SCALE = 3; // Original scale
    public static final int TILE_SIZE = STANDARD_TILE_SIZE * SCALE; // 48x48
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels
    public final int MAX_WORLD_COL = 39;
    public final int MAX_WORLD_ROW = 39;

    int FPS = 60;
    public KeyHandler keyHandler = new KeyHandler();
    Thread gameThread = null;
    public Player player = new Player(this, keyHandler); // Gives this class and key handler
    TileManager tileManager = new TileManager(this);

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
	player.update();	// Calls method from player class/**/

    }
    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g; // Change g to 2D
	tileManager.draw(g2);	// Draw background first
	player.draw(g2);	// Pass the g2 to be able to draw
	g2.dispose();
    }
}
