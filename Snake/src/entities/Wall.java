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
				Game.status = "GAME_OVER";
			}
		}
		
		if (player.getx()[0] < 0) {
			Game.status = "GAME_OVER";
		}
		
		if (player.getx()[0] >= 800) {
			Game.status = "GAME_OVER";
		}
		
		if (player.gety()[0] < 0) {
			Game.status = "GAME_OVER";
		}
		
		if (player.gety()[0] >= 600) {
			Game.status = "GAME_OVER";
		}
		
	}

	public void render(Graphics g) {

		
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	
}
