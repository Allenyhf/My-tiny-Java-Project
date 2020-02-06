package cn.allen.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import org.w3c.dom.css.Rect;

public class Stone extends GameObject {

	boolean exist = false;
	int destroy = 15;
	public Stone() {
		this.exist = true;
		this.width = 80;
		this.height = 80;
		this.randInit();
	}
	
	public Stone(int x, int y, Image img) {
		super(x, y, img);
		// TODO Auto-generated constructor stub
		this.exist = true;
		this.width = 80;
		this.height = 80;
		this.randInit();
	}
	
	public void drawself(Graphics g) {
		if(!isExist())
			return;
		g.drawImage(img, x, y, null);
		
	}
	
	boolean isExist() {
		return this.exist;
	}
	
	void dropDown(Graphics g) {
		this.y += this.speedY;
		this.drawself(g);
	}
	
	void disappear() {
		this.exist = false;
	}
	
	public int getPosiY()
	{
		return this.y;
	}
	
	private void randPosi() {
		this.x = (int)(Constant.FRAME_X * Math.random());
		if(this.x > Constant.FRAME_X - this.width)
			this.x = Constant.FRAME_X - this.width;
	}
	
	private void randSpeedY() {
		this.speedY = (int)(5 * Math.random());
		if(this.speedY == 0)
			this.speedY = 1;
	}
	
	public void randInit() {
		this.randPosi();
		this.randSpeedY();
	}
	

	
}
