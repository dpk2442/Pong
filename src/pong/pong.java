package pong;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import gameObjects.*;
import helpers.*;

public class pong extends Applet implements KeyListener, MouseListener, MouseMotionListener {
	
	Thread displayThread;
	Repaint displayObject;

	int i = 0;
	
	PongPaddle player1 = new PongPaddle(660, 180, 20, 70, false);
	PongPaddle player2 = new PongPaddle(20, 180, 20, 70, true);
	
	ScoreKeeper score = new ScoreKeeper();
	
	PongBall ball = new PongBall(360, 230, 20, 20, player1, player2, score);
	
	
	boolean hasMouseBeenClicked = false;
	
	Graphics bufferGraphics; 
	Image offscreen;
	
	public void init() {
		setSize(700, 500);
		setBackground(Color.black);
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

        offscreen = createImage(700,500); 
        bufferGraphics = offscreen.getGraphics();
	}
	
	public void paint(Graphics g) {
		bufferGraphics.clearRect(0,0,700,500);
		
		bufferGraphics.setColor(Color.green);
		
		if (!hasMouseBeenClicked) bufferGraphics.drawString("Click to begin game.", 200, 300);
		
		player1.draw(bufferGraphics, null);
		player2.draw(bufferGraphics, ball);
		
		score.draw(bufferGraphics);
		
		ball.draw(bufferGraphics);
		
		bufferGraphics.drawLine(350, 0, 350, 500);
		
        g.drawImage(offscreen,0,0,this);
	}

	int keyHit;
	boolean processBool = false;
	ProcessTheKeys processTheKeys = new ProcessTheKeys();
	Thread processInputThread = null;
	
	public void keyPressed(KeyEvent e) {
		keyHit = e.getKeyCode();
		processBool = true;
		if (processInputThread == null) processInputThread = new Thread(processTheKeys);
		try {
			processInputThread.start();
		} catch (IllegalThreadStateException tse) {}
	}
	public void keyReleased(KeyEvent e) {
		processBool = false;
		processInputThread = null;
	}
	
	class ProcessTheKeys implements Runnable {
		public void run() {
			while (processBool) {
				if (keyHit == 38) {
					player1.moveUp();
				} else if (keyHit == 40) {
					player1.moveDown();
				}
				
				try { Thread.sleep(30); } catch (InterruptedException e) {}
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		displayObject = new Repaint(this);
		displayThread = new Thread(displayObject);
		displayThread.start();
		hasMouseBeenClicked = true;
	}
	
	public void mouseMoved(MouseEvent e) {
		if (hasMouseBeenClicked) {
			player1.moveMouse(e.getY());
		}
	}
	
	public void keyTyped(KeyEvent e) {}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}

}
