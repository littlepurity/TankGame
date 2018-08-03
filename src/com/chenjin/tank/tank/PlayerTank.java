package com.chenjin.tank.tank;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.chenjin.tank.client.TankClient;
import com.chenjin.tank.missile.PlayerTankOrdinaryMissile;
import com.chenjin.tank.pojo.Direction;
import com.chenjin.tank.pojo.Faction;
import com.chenjin.tank.pojo.PropertiesManager;

public class PlayerTank extends BaseTank {	
	private static final int X_SPEED = 3;
	private static final int Y_SPEED = 3;
	
	private boolean leftFlag;
	private boolean rightFlag;
	private boolean upFlag;
	private boolean downFlag;
	
	private static final int TANK_LIFE;
	
	private static Map<Direction, Image> tankImagesMap = new HashMap<Direction, Image>();
	
	static {
		tankImagesMap.put(Direction.L, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankL.gif")));
		tankImagesMap.put(Direction.R, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankR.gif")));
		tankImagesMap.put(Direction.U, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankU.gif")));
		tankImagesMap.put(Direction.D, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankD.gif")));
		tankImagesMap.put(Direction.LU, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankLU.gif")));
		tankImagesMap.put(Direction.LD, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankLD.gif")));
		tankImagesMap.put(Direction.RU, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankRU.gif")));
		tankImagesMap.put(Direction.RD, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/playerTank/tankRD.gif")));
		
		String tankLife = PropertiesManager.getProperty("playerTankLife");
		TANK_LIFE = Integer.parseInt(tankLife);
	}
	
	public PlayerTank(int x, int y, TankClient tc) {
		super(x, y, Faction.P, tc);
		
		this.tankDir = Direction.STOP;
		this.barrelDir = Direction.U;
		this.life = TANK_LIFE;
		this.curLife = this.life;
		curImage = tankImagesMap.get(Direction.U);
	}

	@Override
	public void move() {
		if (tankDir != Direction.STOP) {
			curImage = tankImagesMap.get(tankDir);			
		}
		move(X_SPEED, Y_SPEED);
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
		case KeyEvent.VK_CONTROL :
			launchMissile(barrelDir, PlayerTankOrdinaryMissile.class);
			break;
		}
		
		locateDirection();
	}
	
	public void locateDirection() {
		if (leftFlag && !rightFlag && !upFlag && !downFlag) {
			tankDir = Direction.L;
			barrelDir = tankDir;
		} else if (!leftFlag && rightFlag && !upFlag && !downFlag) {
			tankDir = Direction.R;
			barrelDir = tankDir;
		} else if (!leftFlag && !rightFlag && upFlag && !downFlag) {
			tankDir = Direction.U;
			barrelDir = tankDir;
		} else if (!leftFlag && !rightFlag && !upFlag && downFlag) {
			tankDir = Direction.D;
			barrelDir = tankDir;
		} else if (leftFlag && !rightFlag && upFlag && !downFlag) {
			tankDir = Direction.LU;
			barrelDir = tankDir;
		} else if (leftFlag && !rightFlag && !upFlag && downFlag) {
			tankDir = Direction.LD;
			barrelDir = tankDir;
		} else if (!leftFlag && rightFlag && upFlag && !downFlag) {
			tankDir = Direction.RU;
			barrelDir = tankDir;
		} else if (!leftFlag && rightFlag && !upFlag && downFlag) {
			tankDir = Direction.RD;
			barrelDir = tankDir;
		} else if (!leftFlag && !rightFlag && !upFlag && !downFlag) {
			tankDir = Direction.STOP;
		}
	}
}
