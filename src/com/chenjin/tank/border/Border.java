package com.chenjin.tank.border;

import java.awt.Color;
import java.awt.Graphics;

import com.chenjin.tank.base.BaseObject;
import com.chenjin.tank.client.TankClient;

public class Border extends BaseObject {

	public Border(int x, int y, int width, int height, TankClient tc) {
		super(x, y, width, height, tc);
	}
	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		g.drawString("aaaaa", 100, 50);
		g.setColor(c);
	}
	
}
