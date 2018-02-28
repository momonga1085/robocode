package 最強.aim;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public abstract class Aim {
	
	AdvancedRobot myself;
	
	Aim(AdvancedRobot myself) {
		this.myself = myself;
	}
	
	abstract public void fire(ScannedRobotEvent e);

}
