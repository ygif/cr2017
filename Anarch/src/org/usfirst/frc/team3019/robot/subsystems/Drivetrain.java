package org.usfirst.frc.team3019.robot.subsystems;

import org.usfirst.frc.team3019.robot.RobotMap;
import org.usfirst.frc.team3019.robot.commands.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Drivetrain extends Subsystem {
	
	VictorSP leftFrontMotor;
	VictorSP leftRearMotor;
	VictorSP rightFrontMotor;
	VictorSP rightRearMotor;
	RobotDrive rDrive;
	
	public Drivetrain() {
		super();
		leftFrontMotor = new VictorSP(RobotMap.leftFrontMotor);
		leftRearMotor = new VictorSP(RobotMap.leftRearMotor);
		rightFrontMotor = new VictorSP(RobotMap.rightFrontMotor);
		rightRearMotor = new VictorSP(RobotMap.rightRearMotor);
		rDrive = new RobotDrive(leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
	public void arcadeDrive(double moveValue, double rotateValue){
		rDrive.arcadeDrive(moveValue, rotateValue);
	}
}
