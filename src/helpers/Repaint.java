package helpers;

import gameObjects.*;
import pong.*;

public class Repaint implements Runnable {

	private pong mainPong;
	
	public Repaint(pong mainPong) {
		this.mainPong = mainPong;
	}
	
	public void run() {
		while (true) {
			mainPong.repaint();
			
			try { Thread.sleep(SuperGameObject.SleepConst); } catch (InterruptedException e) {}
		}
	}

}
