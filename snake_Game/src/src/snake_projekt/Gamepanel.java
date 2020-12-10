package snake_projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable, KeyListener{
	

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread thread;
	
	private boolean running;
	
	private boolean right = true, left = false, up = false, down = false; 
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r; 
	
	private int xcoor = 10, ycoor = 10, size = 15;
	private int ticks = 0;
	
public Gamepanel() {
	setFocusable(true);
	
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
	addKeyListener(this);
	
	
	snake = new ArrayList<BodyPart>();
	apples = new ArrayList<Apple>();
	
	r = new Random();
	
	start();
}

public void start() {
running = true;
thread = new Thread(this);
thread.start();
	
}
public void stop() {
	running = false;
	try {
		thread.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void tick() {
	if(snake.size() ==0) {
		b = new BodyPart(xcoor, ycoor, 10);
		snake.add(b);
	}
	ticks++;
	if(ticks > 250000) {
		if(right) xcoor++;
		if(left) xcoor--;
		if(up) ycoor--;
		if(down) ycoor++;
		
		ticks = 0;
		
		b = new BodyPart(xcoor, ycoor, 10);
		snake.add(b);
		
		if(snake.size() > size) {
			snake.remove(0);
		}
		
	}
	if(apples.size() ==0) {
		int xcoor = r.nextInt(49);
		int ycoor = r.nextInt(49);
		
		apple = new Apple(xcoor, ycoor, 10);
		apples.add(apple);
	}
	
	for(int i = 0 ; i < apples.size(); i++) {
		if(xcoor ==apples.get(i).getXcoor() && ycoor == apples.get(i).getYcoor()) {
			size++;
			apples.remove(i);
			i++;
		}
	}
	//collision on snake body part
	for(int i = 0 ; i< snake.size() ; i++) {
		if(xcoor == snake.get(i).getXcoor() && ycoor == snake.get(i).getYcoor()) {
			if(i != snake.size()-1) {
				System.out.println("Game Over");
				stop();
			}
		}
	}
	
	//collision on border
	if(xcoor < 0 || xcoor > 49 || ycoor < 0 || ycoor > 49) {
		System.out.println("Game Over");
		stop();
	}
	
}
public void paint(Graphics g) {
	
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, WIDTH, HEIGHT);
	
	for(int i = 0 ; i < WIDTH/10 ; i++) {
		g.drawLine(i * 10, 0 , i*10, HEIGHT);
	}
	for(int i = 0 ; i < HEIGHT/10 ; i++) {
		g.drawLine(0, i*10 , HEIGHT, i * 10);
	}
	for(int i = 0 ; i < snake.size() ; i++) {
		snake.get(i).draw(g);
	}
	for(int i = 0; i < apples.size(); i++) {
		apples.get(i).draw(g);
	}
	
}

@Override
public void run() {
	while(running) {
		tick();
		repaint();
	}
	
}

@Override
public void keyTyped(KeyEvent e) {
	
}

@Override
public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
	if(key == KeyEvent.VK_RIGHT && !left) {
		right = true;
		up = false;
		down = false;
	}
	if(key == KeyEvent.VK_LEFT && !right) {
		left = true;
		up = false;
		down = false;
	}
	if(key == KeyEvent.VK_UP && !down) {
		up = true;
		left = false;
		right = false;
	}
	if(key == KeyEvent.VK_DOWN && !up) {
		down = true;
		left = false;
		right = false;
	}
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}



}
