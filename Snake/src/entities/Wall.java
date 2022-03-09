package entities;

import java.awt.Graphics;

import application.Game;

public class Wall {

	private Player player;

	public Wall(Player player) {
		this.player = player;
	}
	
	public void tick() {

		for (int i = player.getBodyparts(); i > 0; i--) {
			if (player.getx()[0] == player.getx()[i] && player.gety()[0] == player.gety()[i]) {
				Game.isRunning = false;
			}
		}
		
		if (player.getx()[0] < 0) {
			Game.isRunning = false;
		}
		
		if (player.getx()[0] > 800) {
			Game.isRunning = false;
		}
		
		if (player.gety()[0] < 0) {
			Game.isRunning = false;
		}
		
		if (player.gety()[0] > 600) {
			Game.isRunning = false;
		}
		
	}

	public void render(Graphics g) {

		
	}

}
