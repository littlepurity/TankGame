package com.chenjin.tank.collision;

import java.util.List;

import com.chenjin.tank.border.Border;
import com.chenjin.tank.tank.BaseTank;

public class TankAndBordersCollisionDetector implements CollisionDetector<BaseTank, List<Border>> {
	@Override
	public void CollisionDetect(BaseTank bt, List<Border> borders) {
		for (int i = 0; i < borders.size(); i++) {
			if (bt.isCollideWith(borders.get(i))) {
				bt.collideWithBorder();
			}
		}
	}
}
