package com.chenjin.tank.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.chenjin.tank.base.BaseObject;
import com.chenjin.tank.client.TankClient;
import com.chenjin.tank.missile.BaseMissile;
import com.chenjin.tank.pojo.Direction;
import com.chenjin.tank.pojo.Faction;
import com.chenjin.tank.pojo.PropertiesManager;

public abstract class BaseTank extends BaseObject {
	protected Direction tankDir;
	protected Direction barrelDir;
	protected Image curImage;
	protected int oldX;
	protected int oldY;
	protected int step = 0;
	protected int life;
	protected int curLife;
	protected boolean live = true;
	protected Faction faction;
	private static final int TANK_WIDTH;
	private static final int TANK_HEIGHT;
	
	static {
		String tankWidth = PropertiesManager.getProperty("tankWidth");
		String tankHeight = PropertiesManager.getProperty("tankHeight");
		TANK_WIDTH = Integer.parseInt(tankWidth);
		TANK_HEIGHT = Integer.parseInt(tankHeight);
	}
	
	
	public BaseTank(int x, int y, Faction f, TankClient tc) {
		super(x, y, TANK_WIDTH, TANK_HEIGHT, tc);
		this.width = TANK_WIDTH;
		this.height = TANK_HEIGHT;
		this.faction = f;
		this.oldX = x;
		this.oldY = y;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.drawImage(curImage, x, y, null);
		drawLifeBar(g);
		g.setColor(c);
		move();
	}
	
	public void drawLifeBar(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		int width = TANK_WIDTH * curLife / life;
		g.fillRect(x, y-5, width, 5);
		g.setColor(Color.gray);
		g.fillRect(x+width, y-5, TANK_WIDTH-width, 5);
		g.setColor(c);
	}
	
	public abstract void move();
	
	public void move(int speedX, int speedY) {
		oldX = x;
		oldY = y;
		switch (tankDir) {
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
	
	public void stay() {
		x = oldX;
		y = oldY;
		step = 0;
	}
	
	public void launchMissile(Direction dir, Class<? extends BaseMissile> missileClass) {
		if (!isLive()) {
			return;
		}
		BaseMissile bm = null;
		try {
			Constructor<? extends BaseMissile> c = missileClass.getConstructor(int.class, int.class, Direction.class, TankClient.class);
			bm = c.newInstance(x+width/2-5, y+height/2-5, dir, tc);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (bm != null) {
			tc.tankLaunchedMissile(bm);			
		}
	}
	
	public boolean isLive() {
		return live;
	}
	
	public Faction getFaction() {
		return faction;
	}
	
	public void collideWithWall() {
		this.stay();
	}
	
	public void collideWithTank() {
		this.stay();
	}
	
	public void collideWithBorder() {
		this.stay();
	}
	
	public void hittedByMissile(BaseMissile bm) {
		this.curLife -= bm.getPower();
		if (this.curLife <= 0) {
			this.live = false;
			tc.tankIsDead(this);
		}
	}
}
