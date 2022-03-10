package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
	
	private static final long serialVersionUID = 1L;

	private int[] x = new int[4800];
	
	private int[] y = new int[4800];
	
	private int dificult = 10;
	
	private int bodyparts = 3;
	
	private final int HEIGHT = 10;
	
	private final int WIDTH = 10;
	
	public static boolean uppressed, downpressed, rightpressed, leftpresseed;
	
	public Player() {
	}
	
	public Player(int x, int y) {
		for (int i = 0; i < bodyparts; i++) {
			this.x[i] = x - i*dificult;
			this.y[i] = y;
			rightpressed = true;
			uppressed = false;
			downpressed = false;
			leftpresseed = false;
		}
	}
		
	
	public void tick() {
		
		for (int i = bodyparts; i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		if (uppressed) {
			y[0] = y[0] - dificult;
		}
		if (downpressed) {
			y[0] = y[0] + dificult;
			
		}
		if (rightpressed) {
			x[0] = x[0] + dificult;			
		}
		if (leftpresseed) {
			x[0] = x[0] - dificult;
		}
		
	}
	
	public void render(Graphics g) {
		
		for (int i = 0; i< bodyparts ; i++) {
			if (i==0) {
				g.setColor(Color.RED);
				g.fillRect(x[i], y[i], WIDTH, HEIGHT);
			}else {
				g.setColor(Color.PINK);
				g.fillRect(x[i], y[i], WIDTH, HEIGHT);
			}
		
		}
		
	}

	public int[] getx() {
		return x;
	}

	public int[] gety() {
		return y;
	}

	public int getBodyparts() {
		return bodyparts;
	}

	public void bodyAdd() {
		bodyparts++;
	}
	
}
