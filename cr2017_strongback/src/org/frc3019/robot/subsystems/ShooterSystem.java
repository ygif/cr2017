package org.frc3019.robot.subsystems;

import org.frc3019.robot.Constants;
import org.strongback.command.Requirable;
import org.strongback.components.Motor;
import org.frc3019.robot.util.SystemStates;

public class ShooterSystem implements Requirable {
	private Motor shootMotor;

	public ShooterSystem(Motor motor) {
		shootMotor = motor;
	}

	public void stopShooter() {
		shootMotor.stop();
	}

	public void startShooter() {
		shootMotor.setSpeed(1.0 * Constants.SHOOTSPEED_SCALE_FACTOR);
	}
}
