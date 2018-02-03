package org.usfirst.frc.team3019.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.RobotMap;

/**
 *
 */
public class Drive extends Command {

	double move;
	double turn;

	public Drive(int timeout) {
		super(timeout);
		requires(Robot.driveTrain);
	}

	public Drive() {
		super();
		requires(Robot.driveTrain);
	}

	public Drive(double move, double turn) {
		super();
		requires(Robot.driveTrain);
		this.move = move;
		this.turn = turn;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putNumber("move", move);
		SmartDashboard.putNumber("turn", turn);

		if (RobotMap.orientForward) {
			move = -Robot.oi.xbox.getY(Hand.kLeft);
			turn = Robot.oi.xbox.getX(Hand.kLeft);
		} else {
			move = Robot.oi.xbox.getY(Hand.kLeft);
			turn = Robot.oi.xbox.getX(Hand.kLeft);
		}
		
		
		double axis = Robot.oi.xbox.getTriggerAxis(Hand.kRight) * 0.5 + 0.5;
		double throttle = RobotMap.orientForward ? axis : -axis;
		double t = Robot.oi.xbox.getX(Hand.kLeft);
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
