package 最強.movement;

import java.awt.geom.Point2D;
import java.util.Map;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import 最強.Enemy;
import 最強.MyUtil;

public class AntiGravityMovement extends Movement {
	
	int numOfRandomGravityPoints = 2;
	
	int wallGravityConst = 5000;
	
	public AntiGravityMovement(AdvancedRobot myself, Map<String, Enemy> enemyMap) {
		super(myself, enemyMap);
		System.out.println("反重力で動くわ＾＾");
	}
	
	@Override
	public void move(ScannedRobotEvent e) {
		double xForce = 0;
	    double yForce = 0;
		for (String enemyName : enemyMap.keySet()) {
			Enemy enemy = enemyMap.get(enemyName);
			Point2D.Double enemyPoint = enemy.point;
			double absBearing = Utils.normalAbsoluteAngle(Math.atan2(enemyPoint.x - myself.getX(), enemyPoint.y - myself.getY()));
		    double distance = enemyPoint.distance(myself.getX(),myself.getY());
		    xForce -= Math.sin(absBearing) / (distance * distance);
		    yForce -= Math.cos(absBearing) / (distance * distance);
		}
//		System.out.println(Math.random()*myself.getBattleFieldWidth() + "：" +Math.random()*myself.getBattleFieldHeight());
		
		for (int i = 0; i < numOfRandomGravityPoints; i++) {
			Point2D.Double randomPoint = new Point2D.Double(Math.random()*myself.getBattleFieldWidth(), Math.random()*myself.getBattleFieldHeight());
			System.out.println(randomPoint.x + "：" + randomPoint.y);
			double absBearing = Utils.normalAbsoluteAngle(Math.atan2(randomPoint.x - myself.getX(), randomPoint.getY() - myself.getY()));
			double distance = randomPoint.distance(myself.getX(), myself.getY());
			xForce -= Math.sin(absBearing) / (distance * distance);
		    yForce -= Math.cos(absBearing) / (distance * distance);
		}
		
		double nearX = myself.getX() + 100 > myself.getBattleFieldWidth() ? myself.getX() - 100 : myself.getX() + 100;
		double nearY = myself.getY() + 100 > myself.getBattleFieldHeight() ? myself.getY() - 100 : myself.getY() + 100;
		Point2D.Double nearPoint = new Point2D.Double(nearX, nearY);
		double absBearing = Utils.normalAbsoluteAngle(Math.atan2(nearPoint.x - myself.getX(), nearPoint.getY() - myself.getY()));
		double distance = nearPoint.distance(myself.getX(), myself.getY());
		xForce -= Math.sin(absBearing) / (distance * distance);
	    yForce -= Math.cos(absBearing) / (distance * distance);
		
		xForce -= wallGravityConst/Math.pow(MyUtil.getDistance(myself.getX(), myself.getY(), myself.getBattleFieldWidth(), myself.getY()), 3);
		xForce += wallGravityConst/Math.pow(MyUtil.getDistance(myself.getX(), myself.getY(), 0, myself.getY()), 3);
		yForce -= wallGravityConst/Math.pow(MyUtil.getDistance(myself.getX(), myself.getY(), myself.getX(), myself.getBattleFieldHeight()), 3);
		yForce += wallGravityConst/Math.pow(MyUtil.getDistance(myself.getX() ,myself.getY(), myself.getX(), 0), 3);
		
		double angle = Math.atan2(xForce, yForce);
		
		if (xForce == 0 && yForce == 0) {
		    // If no force, do nothing
		} else if(Math.abs(angle - myself.getHeadingRadians())<Math.PI/2){
			myself.setTurnRightRadians(Utils.normalRelativeAngle(angle - myself.getHeadingRadians()));
			myself.setAhead(Double.POSITIVE_INFINITY);
		} else {
			myself.setTurnRightRadians(Utils.normalRelativeAngle(angle + Math.PI - myself.getHeadingRadians()));
			myself.setAhead(-Double.POSITIVE_INFINITY);
		}
	}
	
	public void setNumOfRandomGravityPoints(int numOfRandomGravityPoints) {
		this.numOfRandomGravityPoints = numOfRandomGravityPoints;
	}
	
}
