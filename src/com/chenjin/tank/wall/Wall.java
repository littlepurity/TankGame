package com.chenjin.tank.wall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.chenjin.tank.base.BaseObject;
import com.chenjin.tank.client.TankClient;
import com.chenjin.tank.pojo.PropertiesManager;

public class Wall extends BaseObject {
	private static final int WALL_WIDTH;
	private static final int WALL_HEIGHT;
	private static Image wallImage;
	
	
	static {
		wallImage = tk.getImage(Wall.class.getClassLoader().getResource("images/wall/wall.gif"));
		String wallWidth = PropertiesManager.getProperty("wallWidth");
		String wallHeight = PropertiesManager.getProperty("wallHeight");
		
		WALL_WIDTH = Integer.parseInt(wallWidth);
		WALL_HEIGHT = Integer.parseInt(wallHeight);
	}
	
	public Wall(int x, int y, TankClient tc) {
		super(x, y, WALL_WIDTH, WALL_HEIGHT, tc);
		this.width = WALL_WIDTH;
		this.height = WALL_HEIGHT;
	}

	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.drawImage(wallImage, x, y, null);
		g.setColor(c);
	}

}
