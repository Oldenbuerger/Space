package raumschiff;

import java.awt.Color;

import javax.swing.*;

public class Bullet extends JPanel {
	private int x, y;
	private int xSpeed, ySpeed;
	
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

	public Bullet(Raumschiff r) {
		x = 600;
		y = r.getY()+20;
		xSpeed = r.getxSpeed()+50;
//		ySpeed = r.getySpeed()+50; //falls wir später quer schiessen
		setSize(20,2);
		setBackground(new Color(0x00FF00));
		
		
	}
	public void shoot() {
		x += xSpeed;
		y += ySpeed;
				
	}
}
