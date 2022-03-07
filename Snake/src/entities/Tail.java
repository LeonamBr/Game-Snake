package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tail extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	private int x;
	
	private int y;
	
	private final int HEIGHT = 10;
	
	private final int WIDTH = 10;

	private Player player;
	
	public Tail(Player player) {	
		
		this.x = (int) player.getX()-10;
		this.y = (int) player.getY();
		this.player = player;
	}
	
	public void tick() {
		
		if (Player.uppressed) {
			this.x = (int) player.getX();
			this.y = (int)(player.getY() + 10);
		}
		if(Player.downpressed) {
			this.x = (int) player.getX();
			this.y = (int)(player.getY() - 10);			
		}
		if(Player.leftpresseed) {
			this.x = (int) player.getX() +10;
			this.y = (int) player.getY();			
		}
		
		if(Player.rightpressed) {
			this.x = (int) player.getX() - 10;
			this.y = (int) player.getY();			
		}
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(x, y, WIDTH, HEIGHT);
		
	}

}
