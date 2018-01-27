package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {

	public Climb(){
		super();
		requires(Robot.climberSystem);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void execute(){
		Robot.climberSystem.runMotor();
	}
	
	protected void end(){
		Robot.climberSystem.stopMotor();
	}
	
	protected void interrupted(){		end();
	}
}
