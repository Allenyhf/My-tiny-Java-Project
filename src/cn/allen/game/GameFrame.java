package cn.allen.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.text.StyledEditorKit.BoldAction;

public class GameFrame extends Frame {

	int interCnt =0;
	Image bg = GameUtil.getImage("image/bgr.jpg");
	Image st = GameUtil.getImage("image/stone"+(int)(3*Math.random())
			+ ".png");
	CharaObject co = new CharaObject(0,Constant.FRAME_Y-200,"image/chara.png");
	Stone stone = new Stone(100,100,st);
	
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void launchFrame() {
		this.setTitle("Allen's Product");
		this.setVisible(true);
		this.setSize(Constant.FRAME_X,Constant.FRAME_Y);
		this.setLocation(0, 0);
		
		/*�ر�*/
		this.addWindowListener(new WindowAdapter() {	
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(bg, 0, 0, null);		
//		co.drawSelf(g);
//		co.drawBlood(g);
		co.draw(g);
		stone.drawself(g);
		stone.dropDown(g);

		//��ײ��ʯͷ����Ѫ
		if(co.getRect().intersects(stone.getRect()))
		{
			interCnt ++;
			if(interCnt == 1) {
				co.bloodMinus(stone.destroy);
				stone.disappear();
				stone = new Stone(0,0,st);
				st = GameUtil.getImage("image/stone"+(int)(3*Math.random())
						+ ".png");
				interCnt = 0;
			}
		}
		//ʯͷ������Ұ����ʧ
		if(stone.getPosiY() > Constant.FRAME_Y)
			stone.disappear();
		
		//ʯͷ��ʧ��������ʯͷ
		if(!stone.isExist()) {
			stone = new Stone(0,0,st);
			GameUtil.getImage("image/stone"+(int)(3*Math.random())
					+ ".png");
		}
		
		//�зѪ�����㣬����
		if(co.getBlood() <= 0)
		{
			co.die();
			stone.disappear();
			Color c = g.getColor();
			g.setColor(Color.RED);
			Font f = new Font("����",Font.BOLD,60);
			g.setFont(f);
			g.drawString("GAME OVER",Constant.FRAME_X/2 - 100 ,Constant.FRAME_Y/2 );
			g.setColor(c);
		}
		
	}
	//���������ػ�����
	class PaintThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(true) {
				repaint(); //�ػ�
				//System.out.println("�ػ���");
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			co.setDirection(e);	
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyReleased(e);
			co.resetDirection(e);
		}

	}
	
	
	
	
	public static void main(String[] args) {
		GameFrame gf = new GameFrame();
		gf.launchFrame();
	}
	
}


