package com.chenjin.tank.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chenjin.tank.base.BaseObject;
import com.chenjin.tank.border.Border;
import com.chenjin.tank.collision.CollisionDetector;
import com.chenjin.tank.collision.MissileAndBordersCollisionDetector;
import com.chenjin.tank.collision.MissileAndTanksCollisionDetector;
import com.chenjin.tank.collision.MissileAndWallsCollisionDetector;
import com.chenjin.tank.collision.TankAndBordersCollisionDetector;
import com.chenjin.tank.collision.TankAndTanksCollisionDetector;
import com.chenjin.tank.collision.TankAndWallsCollisionDetector;
import com.chenjin.tank.explode.Explode;
import com.chenjin.tank.missile.BaseMissile;
import com.chenjin.tank.missile.PlayerTankOrdinaryMissile;
import com.chenjin.tank.missile.RobotGreenTankOrdinaryMissile;
import com.chenjin.tank.missile.RobotYellowTankOrdinaryMissile;
import com.chenjin.tank.pojo.PropertiesManager;
import com.chenjin.tank.tank.BaseTank;
import com.chenjin.tank.tank.PlayerTank;
import com.chenjin.tank.tank.RobotGreenTank;
import com.chenjin.tank.tank.RobotYellowTank;
import com.chenjin.tank.wall.Wall;


public class TankClient extends Frame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDGET_WIDTH;
	private static final int WIDGET_HEIGHT;
	private static final int WIDGET_INIT_X;
	private static final int WIDGET_INIT_Y;
	private static final int ROBOT_TANK_YELLOW_INIT_COUNT;
	private static final int ROBOT_TANK_GREEN_INIT_COUNT;
	private static final int BORDER_WIDTH;
	
	private List<Border> borders = new ArrayList<Border>();
	private List<Wall> walls = new ArrayList<Wall>();
	private List<Explode> explodes = new ArrayList<Explode>();
	private List<PlayerTank> playerTanks = new ArrayList<PlayerTank>();
	private List<RobotYellowTank> robotYellowTanks = new ArrayList<RobotYellowTank>();
	private List<RobotGreenTank> robotGreenTanks = new ArrayList<RobotGreenTank>();
	private List<PlayerTankOrdinaryMissile> playerTankOrdinaryMissiles = new ArrayList<PlayerTankOrdinaryMissile>();
	private List<RobotYellowTankOrdinaryMissile> robotYellowTankOrdinaryMissiles = new ArrayList<RobotYellowTankOrdinaryMissile>();
	private List<RobotGreenTankOrdinaryMissile> robotGreenTankOrdinaryMissiles = new ArrayList<RobotGreenTankOrdinaryMissile>();
	private List<List<? extends BaseTank>> tanksList = new ArrayList<List<? extends BaseTank>>();
	private List<List<? extends BaseMissile>> missilesList = new ArrayList<List<? extends BaseMissile>>();
	
	private Map<Class<? extends BaseObject>, List> listMap = new HashMap<Class<? extends BaseObject>, List>();
	
	private Image offScreenImage = null;
	
	private CollisionDetector<BaseMissile, List<Border>> mbcd = new MissileAndBordersCollisionDetector();
	private CollisionDetector<BaseTank, List<Border>> tbcd = new TankAndBordersCollisionDetector();
	private CollisionDetector<BaseTank, List<List<? extends BaseTank>>> ttcd = new TankAndTanksCollisionDetector();
	private CollisionDetector<BaseTank, List<? extends Wall>> twcd = new TankAndWallsCollisionDetector();
	private CollisionDetector<BaseMissile, List<List<? extends BaseTank>>> mtcd = new MissileAndTanksCollisionDetector();
	private CollisionDetector<BaseMissile, List<? extends Wall>> mwcd = new MissileAndWallsCollisionDetector();
	
	static {
		String widgetWidth = PropertiesManager.getProperty("widgetWidth");
		String widgetHeight = PropertiesManager.getProperty("widgetHeight");
		String widgetInitX = PropertiesManager.getProperty("widgetInitX");
		String widgetInitY = PropertiesManager.getProperty("widgetInitY");
		String robotTankYellowInitCount = PropertiesManager.getProperty("robotTankYellowInitCount");
		String robotTankGreenInitCount = PropertiesManager.getProperty("robotTankGreenInitCount");
		String borderWidth = PropertiesManager.getProperty("borderWidth");
		
		WIDGET_WIDTH = Integer.parseInt(widgetWidth);
		WIDGET_HEIGHT = Integer.parseInt(widgetHeight);
		WIDGET_INIT_X = Integer.parseInt(widgetInitX);
		WIDGET_INIT_Y = Integer.parseInt(widgetInitY);
		ROBOT_TANK_YELLOW_INIT_COUNT = Integer.parseInt(robotTankYellowInitCount);
		ROBOT_TANK_GREEN_INIT_COUNT = Integer.parseInt(robotTankGreenInitCount);
		BORDER_WIDTH = Integer.parseInt(borderWidth);
	}
	
	
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(WIDGET_WIDTH, WIDGET_HEIGHT);
		}
		
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, WIDGET_WIDTH, WIDGET_HEIGHT);
		gOffScreen.setColor(c);
		this.paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawString("剩余子弹数量 : " + playerTankOrdinaryMissiles.size(), 100, 100);
		g.drawString("剩余黄色子弹数量 : " + robotYellowTankOrdinaryMissiles.size(), 100, 130);
		g.drawString("剩余绿色子弹数量 : " + robotGreenTankOrdinaryMissiles.size(), 100, 160);
		g.setColor(c);
		paint(g, borders);
		paint(g, walls);
		for (int i = 0; i < tanksList.size(); i++) {
			for (int j = 0; j < tanksList.get(i).size(); j++) {
				BaseTank bt = tanksList.get(i).get(j);
				bt.draw(g);
				tbcd.CollisionDetect(bt, borders);
				ttcd.CollisionDetect(bt, tanksList);
				twcd.CollisionDetect(bt, walls);
			}
		}
		for (int i = 0; i < missilesList.size(); i++) {
			for (int j = 0; j < missilesList.get(i).size(); j++) {
				BaseMissile bm = missilesList.get(i).get(j);
				bm.draw(g);
				mbcd.CollisionDetect(bm, borders);
				mtcd.CollisionDetect(bm, tanksList);
				mwcd.CollisionDetect(bm, walls);
			}
		}
		paint(g, explodes);
	}
	
	public void paint(Graphics g, List<? extends BaseObject> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).draw(g);
		}
	}
	

	public void initBorders() {
		borders.add(new Border(0, 30, BORDER_WIDTH, WIDGET_HEIGHT, this));
		borders.add(new Border(WIDGET_WIDTH-BORDER_WIDTH, 30, BORDER_WIDTH, WIDGET_HEIGHT, this));
		borders.add(new Border(0, 30, WIDGET_WIDTH, BORDER_WIDTH, this));
		borders.add(new Border(0, WIDGET_HEIGHT-BORDER_WIDTH, WIDGET_WIDTH, BORDER_WIDTH, this));
	}
	
	public void initWalls() {
		walls.add(new Wall(300, 300, this));
		walls.add(new Wall(350, 300, this));
		walls.add(new Wall(400, 300, this));
		walls.add(new Wall(450, 300, this));
	}
	
	public void initTanks() {
		playerTanks.add(new PlayerTank(400, 400, this));
		int x = 200;
		int y = 100;
		for (int i = 0; i < ROBOT_TANK_YELLOW_INIT_COUNT; i++) {
			robotYellowTanks.add(new RobotYellowTank(x, y, this));
			x += 60;
		}
		x = 200;
		for (int i = 0; i < ROBOT_TANK_GREEN_INIT_COUNT; i++) {
			robotGreenTanks.add(new RobotGreenTank(x, y+100, this));
			x += 60;
		}
		tanksList.add(playerTanks);
		tanksList.add(robotYellowTanks);
		tanksList.add(robotGreenTanks);
		missilesList.add(playerTankOrdinaryMissiles);
		missilesList.add(robotYellowTankOrdinaryMissiles);
		missilesList.add(robotGreenTankOrdinaryMissiles);
	}
	
	public void initListMap() {
		listMap.put(Border.class, borders);
		listMap.put(Wall.class, walls);
		listMap.put(Explode.class, explodes);
		listMap.put(PlayerTank.class, playerTanks);
		listMap.put(RobotGreenTank.class, robotGreenTanks);
		listMap.put(RobotYellowTank.class, robotYellowTanks);
		listMap.put(PlayerTankOrdinaryMissile.class, playerTankOrdinaryMissiles);
		listMap.put(RobotYellowTankOrdinaryMissile.class, robotYellowTankOrdinaryMissiles);
		listMap.put(RobotGreenTankOrdinaryMissile.class, robotGreenTankOrdinaryMissiles);
	}
	
	public void launchFrame() {
		initBorders();
		initWalls();
		initTanks();
		initListMap();
		this.setBounds(WIDGET_INIT_X, WIDGET_INIT_Y, WIDGET_WIDTH, WIDGET_HEIGHT);
		this.setBackground(Color.BLACK);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
	}
	
	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (playerTanks.size() > 0) {
				playerTanks.get(0).KeyPressed(e);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (playerTanks.size() > 0) {
				playerTanks.get(0).keyReleased(e);
			}
		}
	}
	static int count = 1;
	
	public void tankLaunchedMissile(BaseMissile missile) {
		this.listMap.get(missile.getClass()).add(missile);
	}
	
	public void lifeIsOver(BaseObject object) {
		this.listMap.get(object.getClass()).remove(object);
	}
	
	public void tankIsDead(BaseTank bt) {
		this.listMap.get(bt.getClass()).remove(bt);
		this.explodes.add(new Explode(bt.getX(), bt.getY(), this));
	}

}
