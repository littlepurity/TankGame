package com.chenjin.tank.collision;

import java.util.List;

import com.chenjin.tank.tank.BaseTank;

public class TankAndTanksCollisionDetector implements CollisionDetector<BaseTank, List<List<? extends BaseTank>>> {
	@Override
	public void CollisionDetect(BaseTank bt, List<List<? extends BaseTank>> tanksList) {
		for (int i = 0; i < tanksList.size(); i++) {
			for (int j = 0; j < tanksList.get(i).size(); j++) {
				if (bt != tanksList.get(i).get(j) && bt.isCollideWith(tanksList.get(i).get(j))) {
					bt.collideWithTank();
					return;
				}
			}
		}
	}
}
