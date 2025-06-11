package se.liu.vicbe988.background;

import se.liu.vicbe988.entity.Player;
import se.liu.vicbe988.tile.TileManager;
import se.liu.vicbe988.entity.Mouse;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    protected static final int STANDARD_TILE_SIZE = 16; // 16x16 standard tile
    protected static final int SCALE = 3; // Original scale
    /**Standard pixel size, 48x48*/
    public static final int TILE_SIZE = STANDARD_TILE_SIZE * SCALE;
    /**Maximum screen colum*/
    public static final int MAX_SCREEN_COL = 16;
    /**Maximum screen row*/
    public static final int MAX_SCREEN_ROW = 12;
    /**Screen width calculated by tilesize and maximum sreen columns, 768 pixels*/
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    /**Screen height calculated by tilesize and maximum sreen rows, 576 pixels*/
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    /**Maximum world columns*/
    public static final int MAX_WORLD_COL = 16;
    /**Maximum world rows*/
    public static final int MAX_WORLD_ROW = 12;
    /**FPS for game*/
    public static final int FPS = 60;
    public KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread = null;
    /**Manages collision controll using current gamepanel*/
    public CollisionControll collisionControll = new CollisionControll(this);
    public IGameState gameState = new GameState();
    /**The player, controlled via keyboard input*/
    public Player player = new Player(this, keyHandler, gameState);
    /**The mouse, initialized to fixed position*/
    public Mouse mouse = new Mouse(this, keyHandler, 10 * TILE_SIZE, 10 * TILE_SIZE, gameState);
    /**Manages the game's tile map*/
    public TileManager tileManager = new TileManager(this);
    /**Initializing has won to false (has not won yet)*/

    public GamePanel() {
	// Set size of JPanel class
	this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	this.setBackground(Color.BLACK);
	this.setDoubleBuffered(true); // can improve game rendering performance
	this.addKeyListener(keyHandler); // Listenes for pressed key
	this.setFocusable(true); // Focused to recieve key
    }

    /**
     * Starts the game thread, which calls the run method (this method will be called to start the game loop).
     */
    public void setGameThread() {
	gameThread = new Thread(this);
	gameThread.start(); // Automatically calls run method
    }

    /**
     * Runs game loop
     */
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

    /**
     * Updates the game state and checks collision
     */
    public void update() {
	player.update();
	mouse.update();

	Rectangle playerRect = new Rectangle(player.mapX + player.solidArea.x, player.mapY + player.solidArea.y,
		player.solidArea.width, player.solidArea.height
	);
	Rectangle mouseRect = new Rectangle(mouse.mapX + mouse.solidArea.x, mouse.mapY + mouse.solidArea.y,
		mouse.solidArea.width, mouse.solidArea.height
	);
	if (playerRect.intersects(mouseRect)) {	// If the cat touches the mouse
	    gameState.setHasWon(true);
	}
    }

    /**
     * Paints the game components
     */
    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g; // Change g to 2D
	tileManager.draw(g2);	// Draw background first
	mouse.draw(g2);		// Draw player and mouse, pass g2 to be able to draw
	player.draw(g2);
	if (gameState.hasWon()) {
	    g2.setColor(Color.WHITE);
	    g2.setFont(new Font("Arial", Font.BOLD, 48));
	    String text = "You Won!";
	    int x = (getWidth() - g2.getFontMetrics().stringWidth(text)) / 2; // Center text
	    int y = getHeight() / 2;
	    g2.drawString(text, x, y);
	}
	g2.dispose();
    }
}
