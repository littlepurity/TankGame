package com.chenjin.tank.missile;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.chenjin.tank.client.TankClient;
import com.chenjin.tank.pojo.Direction;
import com.chenjin.tank.pojo.Faction;

public class RobotGreenTankOrdinaryMissile extends BaseMissile {
	private static final int X_SPEED = 5;
	private static final int Y_SPEED = 5;
	
	private static Map<Direction, Image> missileImagesMap = new HashMap<Direction, Image>();
	
	static {
		missileImagesMap.put(Direction.L, 
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileL.gif")));
		missileImagesMap.put(Direction.R,
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileR.gif")));
		missileImagesMap.put(Direction.U,
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileU.gif")));
		missileImagesMap.put(Direction.D,
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileD.gif")));
		missileImagesMap.put(Direction.LU, 
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileLU.gif")));
		missileImagesMap.put(Direction.LD, 
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileLD.gif")));
		missileImagesMap.put(Direction.RU, 
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileRU.gif")));
		missileImagesMap.put(Direction.RD,
				tk.getImage(PlayerTankOrdinaryMissile.class.getClassLoader().getResource("images/missile/robotTank/tankGreen/MissileRD.gif")));
	}
	
	public RobotGreenTankOrdinaryMissile(int x, int y, Direction dir, TankClient tc) {
		super(x, y, dir, Faction.R, 20, tc);
		curImage = missileImagesMap.get(dir);
	}
	
	@Override
	public void move() {
		move(X_SPEED, Y_SPEED);
	}
}
