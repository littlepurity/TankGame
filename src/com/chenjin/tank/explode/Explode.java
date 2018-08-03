package com.chenjin.tank.explode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.chenjin.tank.base.BaseObject;
import com.chenjin.tank.client.TankClient;

public class Explode extends BaseObject {
	private static Image[] explodeImages = new Image[11];
	private int step = 0;
	private static boolean init = false;
	
	static {
		explodeImages[0] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/0.gif"));
		explodeImages[1] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/1.gif"));
		explodeImages[2] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/2.gif"));
		explodeImages[3] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/3.gif"));
		explodeImages[4] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/4.gif"));
		explodeImages[5] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/5.gif"));
		explodeImages[6] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/6.gif"));
		explodeImages[7] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/7.gif"));
		explodeImages[8] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/8.gif"));
		explodeImages[9] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/9.gif"));
		explodeImages[10] = tk.getImage(Explode.class.getClassLoader().getResource("images/explode/10.gif"));
	}
	
	public Explode(int x, int y, TankClient tc) {
		super(x, y, 0, 0, tc);
	}
	
	@Override
	public void draw(Graphics g) {
		if (!init) {
			for (int i = 0; i < explodeImages.length; i++) {
				g.drawImage(explodeImages[i], -100, -100, null);
			}
			init = true;
		}
		if (step <= 10) {
			Color c = g.getColor();
			g.drawImage(explodeImages[step], x, y, null);
			g.setColor(c);
			step++;
		} else {
			tc.lifeIsOver(this);			
		}
	}
}
