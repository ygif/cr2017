package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AgitatorSystem extends Subsystem {

	VictorSP agitatorMotor;
	
	public AgitatorSystem(){
		super();
		agitatorMotor = new VictorSP(RobotMap.agitatorMotor);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void runMotor(){
		agitatorMotor.set(1.0 * RobotMap.AGITATORSPEED_SCALE_FACTOR);
	}
	
	
	public void stopMotor(){
		agitatorMotor.stopMotor();
	}
}