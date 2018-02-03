package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Drive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
/**
 *
 */
public class Drivetrain extends Subsystem {
	
	VictorSP leftFrontMotor;
	VictorSP leftRearMotor;
	VictorSP rightFrontMotor;
	VictorSP rightRearMotor;
	DifferentialDrive dd;
	
	public Drivetrain() {
		super();
		
		leftFrontMotor = new VictorSP(RobotMap.leftFrontMotor);
		leftRearMotor = new VictorSP(RobotMap.leftRearMotor);
		SpeedControllerGroup left = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
		
		rightFrontMotor = new VictorSP(RobotMap.rightFrontMotor);
		rightRearMotor = new VictorSP(RobotMap.rightRearMotor);
		SpeedControllerGroup right = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);
		
		dd = new DifferentialDrive(left, right);
		dd.setSafetyEnabled(false);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
	public void arcadeDrive(double moveValue, double rotateValue){
		dd.arcadeDrive(moveValue * RobotMap.DRIVE_SCALE_FACTOR, rotateValue * RobotMap.DRIVE_SCALE_FACTOR);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		dd.tankDrive(-leftSpeed, -rightSpeed);
	}
}
