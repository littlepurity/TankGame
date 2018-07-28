package com.chenjin.tank.pojo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;



public class Tank {
	private int x;
	private int y;
	private static final int X_SPEED = 1;
	private static final int Y_SPEED = 1;
	private boolean leftFlag = false;
	private boolean rightFlag = false;
	private boolean upFlag = false;
	private boolean downFlag = false;
	
	public static enum Direction {U, D, L, R, LU, LD, RU, RD, STOP};
	private Direction dir = Direction.STOP;
	
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		move();
	}
	
	public void move() {
		switch (dir) {
		case U:
			y -= Y_SPEED;
			break;
		case D:
			y += Y_SPEED;
			break;
		case L:
			x -= X_SPEED;
			break;
		case R:
			x += X_SPEED;
			break;
		case LU:
			x -= X_SPEED;
			y -= Y_SPEED;
			break;
		case LD:
			x -= X_SPEED;
			y += Y_SPEED;
			break;
		case RU:
			x += X_SPEED;
			y -= Y_SPEED;
			break;
		case RD:
			x += X_SPEED;
			y += Y_SPEED;
			break;
		case STOP:
			break;
		}
	}
	
	public void KeyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT :
			leftFlag = true;
			break;
		case KeyEvent.VK_RIGHT :
			rightFlag = true;
			break;
		case KeyEvent.VK_UP :
			upFlag = true;
			break;
		case KeyEvent.VK_DOWN :
			downFlag = true;
			break;
		}
		
		locateDirection();
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT :
			leftFlag = false;
			break;
		case KeyEvent.VK_RIGHT :
			rightFlag = false;
			break;
		case KeyEvent.VK_UP :
			upFlag = false;
			break;
		case KeyEvent.VK_DOWN :
			downFlag = false;
			break;
		}
		
		locateDirection();
	}
	
	public void locateDirection() {
		if (leftFlag && !rightFlag && !upFlag && !downFlag) {
			dir = Direction.L;
		} else if (!leftFlag && rightFlag && !upFlag && !downFlag) {
			dir = Direction.R;
		} else if (!leftFlag && !rightFlag && upFlag && !downFlag) {
			dir = Direction.U;
		} else if (!leftFlag && !rightFlag && !upFlag && downFlag) {
			dir = Direction.D;
		} else if (leftFlag && !rightFlag && upFlag && !downFlag) {
			dir = Direction.LU;
		} else if (leftFlag && !rightFlag && !upFlag && downFlag) {
			dir = Direction.LD;
		} else if (!leftFlag && rightFlag && upFlag && !downFlag) {
			dir = Direction.RU;
		} else if (!leftFlag && rightFlag && !upFlag && downFlag) {
			dir = Direction.RD;
		} else if (!leftFlag && !rightFlag && !upFlag && !downFlag) {
			dir = Direction.STOP;
		}
	}
}
