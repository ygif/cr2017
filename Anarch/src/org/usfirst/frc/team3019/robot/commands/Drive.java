package org.usfirst.frc.team3019.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3019.robot.Robot;

/**
 *
 */
public class Drive extends Command {
	
	public Drive() {
		super();
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double move = -Robot.oi.xbox.getY();
		double turn = -Robot.oi.xbox.getX();
		Robot.driveTrain.arcadeDrive(move, turn);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
