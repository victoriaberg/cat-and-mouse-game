package se.liu.vicbe988.background;

import javax.swing.*;
import java.awt.*;

public class LaunchPage {
    public LaunchPage() {
	JFrame jFrame = new JFrame();
	GamePanel gamePanel = new GamePanel();
	jFrame.add(gamePanel);
	jFrame.pack();

	jFrame.setLocationRelativeTo(null); // Starts jFrame in the middle of screen
	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jFrame.setLayout(null);
	jFrame.setVisible(true); // To see the window
	jFrame.setTitle("2D game");

	gamePanel.setGameThread();
    }
}
