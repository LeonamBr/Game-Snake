package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
	
	private static final long serialVersionUID = 1L;

	private int x;
	
	private int y;
	
	private int dificult = 5;
	
	private final int HEIGHT = 10;
	
	private final int WIDTH = 10;
	
	public static boolean uppressed, downpressed, rightpressed, leftpresseed;
	
	public Player() {
	}
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
		
	
	public void tick() {
		
		if (uppressed) {
			this.y-=dificult;
		}
		if (downpressed) {
			y+=dificult;
		}
		if (rightpressed) {
			x+=dificult;
		}
		if (leftpresseed) {
			x-=dificult;
		}
		
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect(x, y, WIDTH, HEIGHT);
		
	}


	public double getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	

}
