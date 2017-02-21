package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AgitateWhile extends Command {
	
	public AgitateWhile(){
		super();
		requires(Robot.agitatorSystem);
	}
	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void execute(){
		Robot.agitatorSystem.runMotor();
	}
	
	protected void end(){
		Robot.agitatorSystem.stopMotor();
	}
	
	protected void interrupted(){
		Robot.agitatorSystem.stopMotor();
	}
}
