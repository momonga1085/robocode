package 最強.aim;

import robocode.AdvancedRobot;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class SimpleLinerAim extends Aim {

	public SimpleLinerAim(AdvancedRobot myself) {
		super(myself);
	}

	@Override
	public void fire(ScannedRobotEvent e, double bulletPower) {
		double headOnBearing = myself.getHeadingRadians() + e.getBearingRadians();
		double absBearing = e.getBearingRadians() + myself.getHeadingRadians();
		double linearBearing = headOnBearing + Math.asin(
				e.getVelocity() / Rules.getBulletSpeed(bulletPower) * Math.sin(e.getHeadingRadians() - headOnBearing));
		myself.setTurnGunRightRadians(Utils.normalRelativeAngle(linearBearing - myself.getGunHeadingRadians()));
		myself.setTurnRadarRightRadians(Utils.normalRelativeAngle(absBearing - myself.getRadarHeadingRadians())*2);
		myself.setFire(bulletPower);

	}

}
