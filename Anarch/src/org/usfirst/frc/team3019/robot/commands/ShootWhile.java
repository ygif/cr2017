package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootWhile extends Command {

	public ShootWhile(){
		super();
		requires(Robot.shooterSystem);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void execute(){
		Robot.shooterSystem.runMotor();
	}
	
	protected void end() {
		Robot.shooterSystem.stopMotor();
	}
	
	protected void interrupted(){
		end();
	}
}
