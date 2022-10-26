package raumschiff;

import javax.swing.*;


public class Raumschiff extends JLabel {

	private int x, y, xSpeed, ySpeed;
	
	public Raumschiff() {
		x = 100;
		y = 300;
		xSpeed = 5;
		setSize(130,35);
		setIcon(new ImageIcon(Raumschiff.class.getResource("/resources/raumschiff.gif")));
	}
	
	public void move() {
		x += xSpeed;
		y += ySpeed;
		//Raumschiff Grenzgänge
		x = x > 600 ? x - 600 : x;
		x = x < 0 ? x + 600 : x;
		y = y > 600 ? y - 600 : y;
		y = y < 0 ? y + 600 : y;
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
