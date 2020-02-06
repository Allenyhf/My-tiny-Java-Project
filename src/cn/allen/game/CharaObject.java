package cn.allen.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.security.Key;

public class CharaObject extends GameObject {
	boolean live = true;
	boolean left, right, up, down;
	int blood = Constant.BLOOD;
	public CharaObject(int x, int y, String dir) {
		super(x, y, dir);
		// TODO Auto-generated constructor stub
		this.width = 260;
		this.height = 180;
		this.x = x;
		this.y = y;
		this.img = GameUtil.getImage(dir);
	}


	public void drawSelf(Graphics g) {
		if(!isAlive()) return;
		g.drawImage(img, this.x, this.y, null);
		
		if(left) {
			x -= speedX;
		}
		if(right) {
			x += speedX;	
			}
		if(up) {
			y -= speedY;
		}
		if(down) {
			y += speedY;
		}
	}

	public void setImage(String dir) {
		this.img = GameUtil.getImage(dir);
	}
	
	public void die() {
		live = false;
	}
	
	public boolean isAlive() {
		return this.live;
	}
	
	public void drawBlood(Graphics g) {
		Color c = g.getColor();
		Font f = new Font("ËÎÌו", Font.BOLD, 50);
		g.setColor(Color.RED);
		g.setFont(f);
		if(blood > 0)
			g.drawString("blood:"+blood, 80, 80);
		else
			g.drawString("Dead", 80, 80);
		g.setColor(c);
	}
	
	public void draw(Graphics g) {
		this.drawBlood(g);
		this.drawSelf(g);
	}
	
	public void bloodMinus(int b) {
		this.blood -= b;
	}
	
	public void bloodAdd(int b) {
		this.blood += b;
	}
	
	public int getBlood() {
		return this.blood;
	}
	
	public void setDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
	}
	
	public void resetDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	}
	
}
