package org.frc3019.robot.subsystems;

import org.strongback.command.Requirable;
import org.strongback.components.Motor;
import org.strongback.components.ui.ContinuousRange;
import org.frc3019.robot.Constants;

public class PickupSystem implements Requirable {
	private final Motor motor;

	public PickupSystem(Motor pickupMotor) {
		motor = pickupMotor;
	}

	public void stopPickup() {
		motor.stop();
	}

	public void startPickup() {
		motor.setSpeed(1.0 * Constants.PICKUP_SCALE_FACTOR);
	}

	public void reversePickup() {
		motor.setSpeed(-1.0 * Constants.PICKUP_SCALE_FACTOR);
	}

	public void runPickup(ContinuousRange throttleRange) {
		motor.setSpeed(throttleRange.read() * Constants.PICKUP_SCALE_FACTOR);
	}

}
