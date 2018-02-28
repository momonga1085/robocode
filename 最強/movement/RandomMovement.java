package 最強.movement;

import java.util.Map;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import 最強.Enemy;

public class RandomMovement extends Movement {

	// gun variables
	static double enemyVelocities[][] = new double[400][4];
	static int currentEnemyVelocity;
	static int aimingEnemyVelocity;
	double velocityToAimAt;
	boolean fired;
	double oldTime;
	int count;
	int averageCount;

	// movement variables
	static double turn = 2;
	int turnDir = 1;
	int moveDir = 1;
	double oldEnemyHeading;
	double oldEnergy = 100;

	public RandomMovement(AdvancedRobot myself, Map<String, Enemy> enemyMap) {
		super(myself, enemyMap);
		System.out.println("ランダムで動くわ＾＾");
	}

	@Override
	public void move(ScannedRobotEvent e) {
		turn += 0.2 * Math.random();
		if (turn > 8) {
			turn = 2;
		}

		if (oldEnergy - e.getEnergy() <= 3 && oldEnergy - e.getEnergy() >= 0.1) {
			if (Math.random() > .5) {
				turnDir *= -1;
			}
			if (Math.random() > .8) {
				moveDir *= -1;
			}
		}

		myself.setMaxTurnRate(turn);
		myself.setMaxVelocity(12 - turn);
		myself.setAhead(90 * moveDir);
		myself.setTurnLeft(90 * turnDir);
		oldEnergy = e.getEnergy();
	}

}
