package com.chenjin.tank.tank;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.chenjin.tank.client.TankClient;
import com.chenjin.tank.missile.RobotYellowTankOrdinaryMissile;
import com.chenjin.tank.pojo.Direction;
import com.chenjin.tank.pojo.Faction;
import com.chenjin.tank.pojo.PropertiesManager;

public class RobotYellowTank extends BaseTank {
	private static final int X_SPEED = 2;
	private static final int Y_SPEED = 2;
	private static Random random = new Random();
	private static final int TANK_LIFE;
	private static Map<Direction, Image> tankImagesMap = new HashMap<Direction, Image>();
	
	static {
		tankImagesMap.put(Direction.L, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankL.gif")));
		tankImagesMap.put(Direction.R, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankR.gif")));
		tankImagesMap.put(Direction.U, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankU.gif")));
		tankImagesMap.put(Direction.D, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankD.gif")));
		tankImagesMap.put(Direction.LU, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankLU.gif")));
		tankImagesMap.put(Direction.LD, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankLD.gif")));
		tankImagesMap.put(Direction.RU, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankRU.gif")));
		tankImagesMap.put(Direction.RD, tk.getImage(PlayerTank.class.getClassLoader().getResource("images/tank/robotTank/tankYellow/tankRD.gif")));
		
		String tankLife = PropertiesManager.getProperty("robotYellowTankLife");
		TANK_LIFE = Integer.parseInt(tankLife);
	}

	public RobotYellowTank(int x, int y, TankClient tc) {
		super(x, y, Faction.R, tc);
		
		this.tankDir = Direction.STOP;
		this.barrelDir = Direction.D;
		this.life = TANK_LIFE;
		this.curLife = this.life;
		curImage = tankImagesMap.get(Direction.D);
	}

	@Override
	public void move() {
		if (tankDir != Direction.STOP) {
			curImage = tankImagesMap.get(tankDir);			
		}
		move(X_SPEED, Y_SPEED);
		
		if (step <= 0) {
			Direction[] dirs = Direction.values();
			step = random.nextInt(100) + 5;
			int rn = random.nextInt(dirs.length);
			tankDir = dirs[rn];
			if (tankDir != Direction.STOP) {
				barrelDir = tankDir;				
			}
		}
		step--;
		if (random.nextInt(60) > 58) {
			launchMissile(barrelDir, RobotYellowTankOrdinaryMissile.class);
		}
	}
}
