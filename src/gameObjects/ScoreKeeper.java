package gameObjects;

import java.awt.*;

public class ScoreKeeper {
	private int player1Score = 0;
	private int player2Score = 0;
	
	public void addPlayer1() {
		player1Score++;
	}
	
	public void addPlayer2() {
		player2Score++;
	}
	
	public void draw(Graphics g) {
		g.drawString("" + player1Score, 645, 30);
		g.drawString("" + player2Score, 50, 30);
	}
}
