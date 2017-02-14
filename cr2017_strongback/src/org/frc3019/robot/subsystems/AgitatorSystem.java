package org.frc3019.robot.subsystems;

import org.frc3019.robot.Constants;
import org.strongback.command.Requirable;
import org.strongback.components.Motor;

public class AgitatorSystem implements Requirable {
	private Motor agitatorMotor;

	public AgitatorSystem(Motor motor) {
		agitatorMotor = motor;
	}

	public void runAgitator() {
		agitatorMotor.setSpeed(1.0 * Constants.AGITATORSPEED_SCALE_FACTOR);
	}

	public void stopAgitator() {
		agitatorMotor.stop();
	}
}
