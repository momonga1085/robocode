package 最強;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.Condition;
import robocode.CustomEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import 最強.aim.Aim;
import 最強.aim.CircleAim;
import 最強.movement.AntiGravityMovement;
import 最強.movement.Movement;
import 最強.movement.RandomMovement;
import 最強.search.Search;
import 最強.search.SpinningSearch;
import 最強.search.TrackingSearch;

public class 絶対最強王 extends AdvancedRobot {

	
	static Map<String, Enemy> 俺の倒すべき相手達;
	final static int 俺の倒すべき相手達の数 = 1;
	int 生存者数 = 俺の倒すべき相手達の数;
	
	Movement movement;
	Aim aim;
	Search search;
	
	AdvancedRobot myself = this;
	
	static boolean firstOnGame = true;
	

	@Override
	public void run() {
		initialize();

		while (true) {
//			movement.move();
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
		}
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		addEnemy(e);
		Enemy enemy = 俺の倒すべき相手達.get(e.getName());

		aim.fire(e);
		movement.move(e);
		search.search(e);
		execute();
		
		if (movement instanceof AntiGravityMovement) {
			AntiGravityMovement agm = (AntiGravityMovement)movement;
			agm.setNumOfRandomGravityPoints(俺の倒すべき相手達の数 - 生存者数);
		}
		
		
	}
	
	private void addEnemy(ScannedRobotEvent e) {
		String enemyName = e.getName();
		if (!俺の倒すべき相手達.containsKey(enemyName)) {
			俺の倒すべき相手達.put(enemyName, new Enemy());
		}
		double absBearing = e.getBearingRadians() + getHeadingRadians();
		Enemy enemy = 俺の倒すべき相手達.get(enemyName);
		enemy.point = new Point2D.Double(
				getX() + e.getDistance() * Math.sin(absBearing), 
				getY() + e.getDistance() * Math.cos(absBearing)
				);
		enemy.absBearing = absBearing;
		enemy.distance = e.getDistance();
		double oldEnergy = enemy.energy;
		if ((oldEnergy - e.getEnergy( ) <= 3 && oldEnergy - e.getEnergy() >= 0.1)) {
			enemy.isFired = true;
		} else {
			enemy.isFired = false;
		}
		
	}
	

	@Override
	public void onHitRobot(HitRobotEvent e) {
		if (movement instanceof AntiGravityMovement) {
			AntiGravityMovement agm = (AntiGravityMovement)movement;
//			agm.setNumOfRandomGravityPoints(0);
		}
	}
	
	@Override
	public void onRobotDeath(RobotDeathEvent e) {
		生存者数--;
		if (生存者数 == 1) {
			search = new TrackingSearch(this);
		}
	}
	
	@Override
	public void onBulletHit(BulletHitEvent e) {
		movement = new RandomMovement(this, 俺の倒すべき相手達);
	}
	
	@Override
	public void onCustomEvent(CustomEvent e) {
		String eventName = e.getCondition().getName();
		switch (eventName) {
		case "NearWall":
			movement = new AntiGravityMovement(this, 俺の倒すべき相手達);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onHitWall(HitWallEvent event) {
		System.out.println("壁に当たってしまいました。。。");
	}
	
	
	
	
	
	
	
	
	private void initialize() {
		if (firstOnGame) {
			俺の倒すべき相手達 = new HashMap<>();
			firstOnGame = false;
		}
		
		setBodyColor(Color.MAGENTA);
		setBulletColor(Color.MAGENTA);
		setGunColor(Color.GREEN);
		setRadarColor(Color.MAGENTA);
		setScanColor(Color.CYAN);
		
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);
		
//		movement = new AntiGravityMovement(this, 俺の倒すべき相手達);
		movement = new RandomMovement(this, 俺の倒すべき相手達);
		aim = new CircleAim(this);
		search = new SpinningSearch(this);
		
		setMaxVelocity(Rules.MAX_VELOCITY);
		
		if (生存者数 == 1) {
			search = new TrackingSearch(this);
		}
		
		addCustomEvent(new Condition("NearWall") {
			@Override
			public boolean test() {
				return MyUtil.isWall(50, myself);
			}
		});
	}
}
