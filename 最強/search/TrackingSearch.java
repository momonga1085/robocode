package 最強.search;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class TrackingSearch extends Search {

	public TrackingSearch(AdvancedRobot myself) {
		super(myself);
	}

	@Override
	public void search(ScannedRobotEvent e) {
		double absoluteBearing = myself.getHeadingRadians() + e.getBearingRadians();
		myself.setTurnRadarRightRadians(
				Utils.normalRelativeAngle(absoluteBearing - myself.getRadarHeadingRadians()) * 2);
	}
	
	

}
