package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Food extends Rectangle {

	private static final long serialVersionUID = 1L;

	private int x;

	private int y;

	private int score = 0;

	private final int HEIGHT = 10;

	private final int WIDTH = 10;

	private Player player;

	Random random = new Random();

	public Food() {

		x = random.ints(0, 800 - 10).findFirst().getAsInt();
		y = random.ints(0, 600 - 10).findFirst().getAsInt();
	}

	public void tick() {

		if (((player.getX() >= x && player.getX() <= x + 10)
				|| (player.getX() + 10 >= x && player.getX() + 10 <= x + 10))
				&& ((player.getY() >= y && player.getY() <= y + 10)
						|| (player.getY() + 10 >= y && player.getY() + 10 <= y + 10))) {
			score += 10;
			x = random.ints(0, 800 - 10).findFirst().getAsInt();
			y = random.ints(0, 600 - 10).findFirst().getAsInt();
		}

	}

	public void render(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.drawString("Score: " + score, 10, 590);

	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
