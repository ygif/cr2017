package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;

public class ShootWhile extends Command {
	double speed = 0;
	public ShootWhile(){
		super();
		requires(Robot.shooterSystem);
	}
	public ShootWhile(double speed){
		super();
		requires(Robot.shooterSystem);
		this.speed = speed;
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void execute(){
		if(RobotState.isAutonomous()){
		Robot.shooterSystem.setMotorSpeed(speed);	
		}else{
		Robot.shooterSystem.runMotor();
		}
	}
	
	protected void end() {
		Robot.shooterSystem.stopMotor();
	}
	
	protected void interrupted(){
		end();
	}
}
