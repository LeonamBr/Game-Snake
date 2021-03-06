package application;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import entities.Food;
import entities.GameOver;
import entities.Menu;
import entities.Player;
import entities.Wall;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	public static boolean isRunning = true;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static boolean enterpressed = false;
	public static boolean showMessage = true;
	public static int framesGameOver = 0;

	public static String status = "MENU";

	private BufferedImage image;

	public static Player player;
	public static Food food;
	public static Wall wall;
	public static GameOver gameOver;
	public static Menu menu;

	public void initFrame() {
		frame = new JFrame("Snake AWT");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Game() {

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initFrame();

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.addKeyListener(this);
		player = new Player(400, 300);
		Player.rightpressed = true;
		food = new Food();
		food.setPlayer(player);
		wall = new Wall(player);
		gameOver = new GameOver();
		menu = new Menu();

	}

	public synchronized void start() {

		thread = new Thread(this);
		isRunning = true;
		thread.start();

	}

	public synchronized void stop() {

		isRunning = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void tick() {

		if (status == "NORMAL") {
			player.tick();
			food.tick();
			wall.tick();
		} else if (status == "GAME_OVER") {

			gameOver.tick();

		} else if (status == "MENU") {

			menu.tick();

		}

	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// ====================== ENTIDADES ========================= //

		food.render(g);
		player.render(g);
		wall.render(g);

		// ========================================================== //

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);

		gameOver.render(g);
		menu.render(g);

		bs.show();

	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountofTicks = 30.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();

		while (isRunning) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {

				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;

			}

		}

		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP && Player.downpressed == false) {
			if (status == "NORMAL") {
				Player.uppressed = true;
				Player.downpressed = false;
				Player.leftpresseed = false;
				Player.rightpressed = false;
			}

			if (status == "MENU") {
				Menu.upPressed = true;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && Player.uppressed == false) {
			if (status == "NORMAL") {
				Player.downpressed = true;
				Player.uppressed = false;
				Player.leftpresseed = false;
				Player.rightpressed = false;
			}
			
			if (status == "MENU") {
				Menu.downPressed = true;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT && Player.rightpressed == false) {
			Player.leftpresseed = true;
			Player.uppressed = false;
			Player.downpressed = false;
			Player.rightpressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT && Player.leftpresseed == false) {
			Player.rightpressed = true;
			Player.uppressed = false;
			Player.downpressed = false;
			Player.leftpresseed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterpressed = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP && Player.downpressed == false) {

			if (status == "MENU") {
				Menu.upPressed = false;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && Player.uppressed == false) {

			if (status == "MENU") {
				Menu.downPressed = false;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterpressed = false;
		}

	}

}
