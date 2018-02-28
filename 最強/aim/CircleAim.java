package 最強.aim;

import java.awt.geom.Point2D;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class CircleAim extends Aim {

	public CircleAim(AdvancedRobot myself) {
		super(myself);
	}

	double oldEnemyHeading = 0;

	@Override
	public void fire(ScannedRobotEvent e) {
		double bulletPower = Math.min(3.0, myself.getEnergy());
		double myX = myself.getX();
		double myY = myself.getY();
		double absoluteBearing = myself.getHeadingRadians() + e.getBearingRadians();
		double enemyX = myself.getX() + e.getDistance() * Math.sin(absoluteBearing);
		double enemyY = myself.getY() + e.getDistance() * Math.cos(absoluteBearing);
		double enemyHeading = e.getHeadingRadians();
		double enemyHeadingChange = enemyHeading - oldEnemyHeading;
		double enemyVelocity = e.getVelocity();
		oldEnemyHeading = enemyHeading;

		double deltaTime = 0;
		double battleFieldHeight = myself.getBattleFieldHeight();
		double battleFieldWidth = myself.getBattleFieldWidth();
		double predictedX = enemyX;
		double predictedY = enemyY;
		
		while ((++deltaTime) * (20.0 - 3.0 * bulletPower) < Point2D.Double.distance(myX, myY, predictedX, predictedY)) {
			predictedX += Math.sin(enemyHeading) * enemyVelocity;
			predictedY += Math.cos(enemyHeading) * enemyVelocity;
			enemyHeading += enemyHeadingChange;
			if (predictedX < 18.0 || predictedY < 18.0 || predictedX > battleFieldWidth - 18.0
					|| predictedY > battleFieldHeight - 18.0) {

				predictedX = Math.min(Math.max(18.0, predictedX), battleFieldWidth - 18.0);
				predictedY = Math.min(Math.max(18.0, predictedY), battleFieldHeight - 18.0);
				break;
			}
		}
		double theta = Utils.normalAbsoluteAngle(Math.atan2(predictedX - myself.getX(), predictedY - myself.getY()));

		myself.setTurnRadarRightRadians(
				Utils.normalRelativeAngle(absoluteBearing - myself.getRadarHeadingRadians()) * 2);
		myself.setTurnGunRightRadians(Utils.normalRelativeAngle(theta - myself.getGunHeadingRadians()));
		if (myself.getGunHeat() == 0) {
			myself.fire(bulletPower);
		}
	}

}
