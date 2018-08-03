package com.chenjin.tank.collision;

import java.util.List;

import com.chenjin.tank.tank.BaseTank;
import com.chenjin.tank.wall.Wall;

public class TankAndWallsCollisionDetector implements CollisionDetector<BaseTank, List<? extends Wall>> {
	@Override
	public void CollisionDetect(BaseTank bt, List<? extends Wall> walls) {
		for (int i = 0; i < walls.size(); i++) {
			if (bt.isCollideWith(walls.get(i))) {
				bt.collideWithWall();
			}
		}
	}
	
}
