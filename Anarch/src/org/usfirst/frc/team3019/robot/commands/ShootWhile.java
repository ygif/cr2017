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
		if(Robot.oi.xbox.getRawAxis(3) > 0){
			Robot.shooterSystem.runMotor();
		} else if (Robot.oi.xbox.getRawAxis(2) > 0){
			Robot.shooterSystem.reverseMotor();
		}
	}
	
	protected void end() {
		Robot.shooterSystem.stopMotor();
	}
	
	protected void interrupted(){
		end();
	}
}
