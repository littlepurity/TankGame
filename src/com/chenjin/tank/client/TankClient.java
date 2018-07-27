package com.chenjin.tank.client;

import java.awt.Frame;

import sun.launcher.resources.launcher_fr;

public class TankClient extends Frame {
	
	public void launchFrame() {
		this.setBounds(400, 200, 800, 600);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
	}

}
