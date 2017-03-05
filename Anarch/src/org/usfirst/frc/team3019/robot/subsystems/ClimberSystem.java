package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSystem extends Subsystem {

	VictorSP climbMotor;
	
	public ClimberSystem(){
		super();
		climbMotor = new VictorSP(RobotMap.climberMotor);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void runMotor(){
		climbMotor.set(1.0 * RobotMap.CLIMBSPEED_SCALE_FACTOR);
	}
	
	public void stopMotor(){
		climbMotor.stopMotor();
	}
}
