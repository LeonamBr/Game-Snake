package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements Runnable, KeyListener {

	private Thread thread;
	private boolean isRunning = true;

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();

	}

	public synchronized void stop() {

	}

	public void tick() {

	}

	public void render() {

	}

	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			
			long now = System.nanoTime();
			delta+= (now - lastTime)/ ns;
			lastTime = now;
			if(delta >=1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if (System.currentTimeMillis() - timer >=1000) {
				
				System.out.println("FPS: "+frames);
				frames = 0;
				timer+=1000;
				
			}
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
