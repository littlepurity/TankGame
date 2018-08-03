package com.chenjin.tank.base;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.chenjin.tank.client.TankClient;

public abstract class BaseObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected TankClient tc;
	protected static Toolkit tk = Toolkit.getDefaultToolkit();
	
	public BaseObject() {}
	
	public BaseObject(int x, int y, int width, int height, TankClient tc) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tc = tc;
	}
	
	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public boolean isCollideWith(BaseObject bo) {
		return this.getRect().intersects(bo.getRect());
	}
	
	public abstract void draw(Graphics g);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
