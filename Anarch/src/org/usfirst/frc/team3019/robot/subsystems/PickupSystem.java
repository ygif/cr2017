package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.VictorSP;

public class PickupSystem extends Subsystem{

	VictorSP pickupMotor = new VictorSP(RobotMap.pickupMotor);
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	public void runMotor() {
		pickupMotor.set(1.0 * RobotMap.PICKUP_SCALE_FACTOR);
	}
	
	public void reverseMotor() {
		pickupMotor.set(-1.0 * RobotMap.PICKUP_SCALE_FACTOR);
	}
	
	public void stopMotor() {
		pickupMotor.stopMotor();
	}

}
