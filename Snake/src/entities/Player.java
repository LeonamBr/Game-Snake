package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
	
	private static final long serialVersionUID = 1L;

	private int x;
	
	private int y;
	
	private final int HEIGHT = 10;
	
	private final int WIDTH = 10;
	
	public static boolean uppressed, downpressed, rightpressed, leftpresseed;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	
	public void tick() {
		
		if (uppressed) {
			this.y-=3;
		}
		if (downpressed) {
			y+=3;
		}
		if (rightpressed) {
			x+=3;
		}
		if (leftpresseed) {
			x-=3;
		}
		
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect(x, y, WIDTH, HEIGHT);
		
	}

}
