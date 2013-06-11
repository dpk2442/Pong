package gameObjects;

import java.awt.*;

public class SuperGameObject {
	int xPos;
	int yPos;
	int height;
	int width;
	
	static int MoveConst = 8;
	static int AIMoveConst = 3;
	static int BallMoveConst = 6;
	public static long SleepConst = 30;
	
	public SuperGameObject(int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
	}
	
	public void draw(Graphics g, PongBall ball) {}
}
