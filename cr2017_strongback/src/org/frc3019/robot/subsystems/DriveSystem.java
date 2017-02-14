package org.frc3019.robot.subsystems;

import org.frc3019.robot.Constants;
import org.strongback.command.Requirable;
import org.strongback.drive.TankDrive;

public class DriveSystem implements Requirable {
	private TankDrive tankDrive;

	public DriveSystem(TankDrive tDrive) {
		this.tankDrive = tDrive;
	}

	public void arcadeDrive(double driveSpeed, double turnSpeed) {
		tankDrive.arcade(driveSpeed * Constants.DRIVE_SCALE_FACTOR, turnSpeed);
	}

	public void stop() {
		tankDrive.stop();
	}

}
