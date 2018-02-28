package 最強.search;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public abstract class Search {
	
	AdvancedRobot myself;
	
	Search (AdvancedRobot myself) {
		this.myself = myself;
	}
	
	abstract public void search(ScannedRobotEvent e);

}
