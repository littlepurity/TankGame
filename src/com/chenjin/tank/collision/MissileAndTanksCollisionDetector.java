package com.chenjin.tank.collision;

import java.util.List;

import com.chenjin.tank.missile.BaseMissile;
import com.chenjin.tank.tank.BaseTank;

public class MissileAndTanksCollisionDetector implements CollisionDetector<BaseMissile, List<List<? extends BaseTank>>> {
	@Override
	public void CollisionDetect(BaseMissile bm, List<List<? extends BaseTank>> tanksList) {
		for (int i = 0; i < tanksList.size(); i++) {
			for (int j = 0; j < tanksList.get(i).size(); j++) {
				if (!bm.isLive()) {
					return;
				}
				if (bm.isCollideWith(tanksList.get(i).get(j))) {
					bm.collideWithTank(tanksList.get(i).get(j));
				}
			}
		}
	}

}
