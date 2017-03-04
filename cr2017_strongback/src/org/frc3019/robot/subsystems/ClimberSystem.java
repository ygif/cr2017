package org.frc3019.robot.subsystems;

import org.frc3019.robot.Constants;
import org.strongback.command.Requirable;
import org.strongback.components.Motor;
import org.strongback.components.ui.ContinuousRange;

public class ClimberSystem implements Requirable {
	private Motor climberMotor;

	public ClimberSystem(Motor climbMotor) {
		climberMotor = climbMotor;
	}

	public void startClimb(ContinuousRange climbRange) {
		if (climbRange.read() > 0.2) {
			climberMotor.setSpeed(climbRange.read() * Constants.CLIMBSPEED_SCALE_FACTOR);
		}
	}

	public void stopClimb() {
		climberMotor.stop();
	}
}
