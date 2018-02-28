package 最強.movement;

import java.util.Map;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import 最強.Enemy;

public abstract class Movement {
	
	AdvancedRobot myself;
	Map<String, Enemy> enemyMap;
	
	public Movement(AdvancedRobot myself, Map<String, Enemy> enemyMap) {
		this.myself = myself;
		this.enemyMap = enemyMap;
	}
	
	abstract public void move(ScannedRobotEvent e);
	

}
