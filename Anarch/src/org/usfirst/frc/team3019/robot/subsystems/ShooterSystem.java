package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSystem extends Subsystem {
	
	VictorSP shootMotor;
	
	public ShooterSystem(){
		super();
		shootMotor = new VictorSP(RobotMap.shooterMotor);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void runMotor(){
		shootMotor.set(RobotMap.SHOOTSPEED_SCALE_FACTOR);
	}
	public void setMotorSpeed(double speed){
		shootMotor.set(speed);
	}
	
	public void reverseMotor(){
		shootMotor.set(-RobotMap.SHOOTSPEED_SCALE_FACTOR);
	}
	
	public void stopMotor(){
		shootMotor.stopMotor();
	}
}
