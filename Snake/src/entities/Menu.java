package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import application.Game;

public class Menu {

	private String[] opcao = { "Novo Jogo", "Sair" };
	public int currentOption = 0;
	public int maxOption = opcao.length - 1;

	public static boolean upPressed = false;
	public static boolean downPressed = false;

	public void tick() {

		
		if (upPressed) {
			upPressed = false;
			currentOption--;
			if (currentOption < 0)
				currentOption = maxOption;
		}

		if (downPressed) {
			downPressed = false;
			currentOption++;
			if (currentOption > maxOption)
				currentOption = 0;
		}
		
		if(Game.enterpressed) {
			Game.enterpressed = false;
			if(Game.status == "MENU") {
				if(opcao[currentOption] == "Novo Jogo")
					Game.status = "NORMAL";
				else if(opcao[currentOption] == "Sair")
					System.exit(1);
			} 
		}

	}

	public void render(Graphics g) {
		if (Game.status == "MENU") {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0, 0, 0, 255));
			g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

			g2.setColor(Color.GREEN);
			g2.setFont(new Font("Arial", 3, 60));
			g2.drawString("Snake", 310, 150);

			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", 3, 30));
			g2.drawString("Novo Jogo", 320, 350);
			g2.drawString("Sair", 370, 400);

			if (opcao[currentOption] == "Novo Jogo") {
				g2.drawString(" >                      <", 280, 350);
			}
			if (opcao[currentOption] == "Sair") {
				g2.drawString(" >          <", 330, 400);
			}

		}
	}

}
