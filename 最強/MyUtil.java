package 最強;

import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Map;

import robocode.AdvancedRobot;

public class MyUtil {
	
	public static double getDistance (double x1,double y1, double x2,double y2) {
	    double x = x2 - x1;
	    double y = y2 - y1;
	    double distance = Math.sqrt(x*x + y*y);
	    return distance;
	}
	
	public static Enemy getNearestEnemy (Map<String, Enemy> enemyMap) {
		double min = Double.MAX_VALUE;
		Enemy minEnemy = null;
		for (String enemyName : enemyMap.keySet()) {
			Enemy enemy = enemyMap.get(enemyName);
		    double distance = enemy.distance;
		    if (distance < min) {
		    	min = distance;
		    	minEnemy = enemy;
		    }
		}
		return minEnemy;
	}
	
	public static boolean isWall(double margin, AdvancedRobot myself) {
		 RoundRectangle2D.Double field = new RoundRectangle2D.Double(
				 margin,
				 margin,
				 myself.getBattleFieldWidth() - margin * 2, 
				 myself.getBattleFieldHeight() - margin * 2,
				 75, 75
			);
		 
		 return !field.contains(new Point2D.Double(myself.getX(), myself.getY()));
	}

}
