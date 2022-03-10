package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import application.Game;

public class GameOver {

	public void tick() {

		Game.framesGameOver++;
		if (Game.framesGameOver == 30) {
			Game.framesGameOver = 0;
			if (Game.showMessage)
				Game.showMessage = false;
			else
				Game.showMessage = true;
		}

		if (Game.enterpressed == true) {

			Game.enterpressed = false;
			Game.player = new Player(400, 300);

			Player.rightpressed = true;
			Game.food = new Food();
			Game.food.setPlayer(Game.player);
			Game.wall = new Wall(Game.player);

			Player.rightpressed = true;
			Player.leftpresseed = false;
			Player.downpressed = false;
			Player.uppressed = false;

			Game.status = "MENU";
		}

	}

	public void render(Graphics g) {

		if (Game.status == "GAME_OVER") {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255, 255, 255, 200));
			g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g2.setColor(Color.red);
			g2.setFont(new Font("Arial", 3, 50));
			g2.drawString("Game Over", 280, 200);
			g2.setFont(new Font("Arial", 3, 30));
			if (Game.showMessage)
				g2.drawString("# Aperte ENTER para jogar continuar #", 120, 350);
		}

	}

}
