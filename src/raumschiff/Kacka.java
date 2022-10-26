package raumschiff;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Kacka extends JLabel{

	private int x, y, xSpeed, ySpeed;
	private Random r = new Random();

	public Kacka() {
		x = 600;
		y = r.nextInt(580);
		xSpeed = -20;
		setSize(50,50);
		setIcon(new ImageIcon(Raumschiff.class.getResource("/resources/kack.gif")));
		
	}

	public void move() {
		x += xSpeed;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
}
