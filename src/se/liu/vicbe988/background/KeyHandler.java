package se.liu.vicbe988.background;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean up, down, left, right;
    @Override public void keyTyped(final KeyEvent e) {
    }

    @Override public void keyPressed(final KeyEvent e) {
	int code = e.getKeyCode(); // Checks which key got pressed
	if(code == KeyEvent.VK_W){
	    up = true;
	}
	if(code == KeyEvent.VK_A){
	    left = true;
	}
	if(code == KeyEvent.VK_S){
	    down = true;
	}
	if(code == KeyEvent.VK_D){
	    right = true;
	}
    }

    @Override public void keyReleased(final KeyEvent e) {
	int code = e.getKeyCode();
	if(code == KeyEvent.VK_W){
	    up = false;
	}
	if(code == KeyEvent.VK_A){
	    left = false;
	}
	if(code == KeyEvent.VK_S){
	    down = false;
	}
	if(code == KeyEvent.VK_D){
	    right = false;
	}
    }
}
