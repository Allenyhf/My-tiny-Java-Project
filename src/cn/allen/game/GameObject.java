package cn.allen.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	public int x;
	public int y;
	protected int speedX=5,speedY=3;
	protected Image img;
	protected int width,height;
	
	public GameObject() {
	}
	
	public GameObject(int x, int y, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.img = img;
	}

	public GameObject(int x, int y, int speedX, int speedY, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.img = img;
	}
	
	public GameObject(int x, int y, String dir) {
		super();
		this.x = x;
		this.y = y;
		this.img = GameUtil.getImage(dir);
	}
	
	public void setX(int _x) {
		this.x = _x;
	}
	
	public void setY(int _y) {
		this.y = _y;
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int setSpeedX(int _speedX) {
		this.speedX  = _speedX;
		return this.speedX;
	}
	
	public int setSpeedY(int _speedY) {
		this.speedY = _speedY;
		return this.speedY;
	}
	
	Rectangle getRect() {
		return new Rectangle(x, y, (int)width, (int)height);
	}
	
}
