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
import entities.Player;
import entities.Wall;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	public static boolean isRunning = true;
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	
	private BufferedImage image;

	public Player player;
	public Food food;
	public Wall wall;

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

		player.tick();
		food.tick();
		wall.tick();

	}

	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// ====================== ENTIDADES ========================= //
		
		food.render(g);
		player.render(g);
		wall.render(g);
		
		
		// ========================================================== //
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
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
			Player.uppressed = true;
			Player.downpressed = false;
			Player.leftpresseed = false;
			Player.rightpressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && Player.uppressed == false) {
			Player.uppressed = false;
			Player.downpressed = true;
			Player.leftpresseed = false;
			Player.rightpressed = false;
		}
	
		if (e.getKeyCode() == KeyEvent.VK_LEFT && Player.rightpressed == false) {
			Player.uppressed = false;
			Player.downpressed = false;
			Player.leftpresseed = true;
			Player.rightpressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && Player.leftpresseed == false) {
			Player.uppressed = false;
			Player.downpressed = false;
			Player.leftpresseed = false;
			Player.rightpressed = true;
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

}
