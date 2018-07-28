package com.chenjin.tank.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.chenjin.tank.pojo.Tank;


public class TankClient extends Frame {
	
	private static final int WIDGET_WIDTH = 800;
	private static final int WIDGET_HEIGHT = 600;
	
	private Tank tank = new Tank(50, 50);
	
	Image offScreenImage = null;
	
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(WIDGET_WIDTH, WIDGET_HEIGHT);
		}
		
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, WIDGET_WIDTH, WIDGET_HEIGHT);
		gOffScreen.setColor(c);
		this.paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		tank.draw(g);
	}

	public void launchFrame() {
		this.setBounds(400, 200, WIDGET_WIDTH, WIDGET_HEIGHT);
		this.setBackground(Color.GREEN);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
	}
	
	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			tank.KeyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			tank.keyReleased(e);
		}
	}

}
