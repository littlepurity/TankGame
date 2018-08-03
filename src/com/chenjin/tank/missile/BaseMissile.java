package com.chenjin.tank.missile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.chenjin.tank.base.BaseObject;
import com.chenjin.tank.client.TankClient;
import com.chenjin.tank.pojo.Direction;
import com.chenjin.tank.pojo.Faction;
import com.chenjin.tank.pojo.PropertiesManager;
import com.chenjin.tank.tank.BaseTank;

public abstract class BaseMissile extends BaseObject {
	protected Direction missileDir;
	protected Image curImage;
	
	protected int power;
	protected static Map<Direction, Image> missileImagesMap = new HashMap<Direction, Image>();
	
	protected boolean live = true;
	protected Faction faction;
	private static final int MISSILE_WIDTH;
	private static final int MISSILE_HEIGHT;
	
	static {
		String missileWidth = PropertiesManager.getProperty("missileWidth");
		String missileHeight = PropertiesManager.getProperty("missileHeight");
		MISSILE_WIDTH = Integer.parseInt(missileWidth);
		MISSILE_HEIGHT = Integer.parseInt(missileHeight);
	}
	
	public BaseMissile() {}
	public BaseMissile(int x, int y, Direction dir, Faction f, int power, TankClient tc) {
		super(x, y, MISSILE_WIDTH, MISSILE_HEIGHT, tc);
		this.missileDir = dir;
		this.faction = f;
		this.power = power;
		this.width = MISSILE_WIDTH;
		this.height = MISSILE_HEIGHT;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.drawImage(curImage, x, y, null);
		g.setColor(c);
		
		move();
	}
	
	public abstract void move();
	
	public void move(int speedX, int speedY) {
		switch (missileDir) {
		case L:
			x -= speedX;
			break;
		case R:
			x += speedX;
			break;
		case U:
			y -= speedY;
			break;
		case D:
			y += speedY;
			break;
		case LU:
			x -= speedX;
			y -= speedY;
			break;
		case LD:
			x -= speedX;
			y += speedY;
			break;
		case RU:
			x += speedX;
			y -= speedY;
			break;
		case RD:
			x += speedX;
			y += speedY;
			break;
		case STOP:
			break;
		}
	}
	
	public boolean isLive() {
		return live;
	}
	
	public Faction getFaction() {
		return faction;
	}
	
	public int getPower() {
		return power;
	}
	
	public void collideWithBorder() {
		this.live = false;
		tc.lifeIsOver(this);
	}
	
	public void collideWithTank(BaseTank bt) {
		if (bt.getFaction() != this.faction) {
			bt.hittedByMissile(this);
			tc.lifeIsOver(this);
		}
	}
	
	public void collideWithWall() {
		this.collideWithBorder();
	}
}
