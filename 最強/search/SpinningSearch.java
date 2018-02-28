package 最強.search;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class SpinningSearch extends Search {

	public SpinningSearch(AdvancedRobot myself) {
		super(myself);
	}

	@Override
	public void search(ScannedRobotEvent e) {
		myself.setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
	}
	
	

}
