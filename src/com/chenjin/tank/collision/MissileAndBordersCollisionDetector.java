package com.chenjin.tank.collision;

import java.util.List;

import com.chenjin.tank.border.Border;
import com.chenjin.tank.missile.BaseMissile;

public class MissileAndBordersCollisionDetector implements CollisionDetector<BaseMissile, List<Border>> {
	@Override
	public void CollisionDetect(BaseMissile bm, List<Border> borders) {
		for (int i = 0; i < borders.size(); i++) {
			if (bm.isCollideWith(borders.get(i))) {
				bm.collideWithBorder();
			}
		}
	}
}
