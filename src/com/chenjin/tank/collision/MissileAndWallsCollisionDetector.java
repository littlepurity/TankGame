package com.chenjin.tank.collision;

import java.util.List;

import com.chenjin.tank.missile.BaseMissile;
import com.chenjin.tank.wall.Wall;

public class MissileAndWallsCollisionDetector implements CollisionDetector<BaseMissile, List<? extends Wall>> {
	@Override
	public void CollisionDetect(BaseMissile bm, List<? extends Wall> walls) {
		for (int i = 0; i < walls.size(); i++) {
			if (bm.isCollideWith(walls.get(i))) {
				bm.collideWithBorder();
			}
		}
	}

}
