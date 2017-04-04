package org.usfirst.frc.team3019.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
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
	public Drive() {
		super();
		requires(Robot.driveTrain);
	}
	public Drive(double move, double turn){
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
		if(RobotState.isOperatorControl()){
			
		if(RobotMap.orientForward){	
		move = -Robot.oi.xbox.getY();
		turn = -Robot.oi.xbox.getX();
		}else{
			move = Robot.oi.xbox.getY();
			turn = Robot.oi.xbox.getX();
		}
		
		
		}
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
