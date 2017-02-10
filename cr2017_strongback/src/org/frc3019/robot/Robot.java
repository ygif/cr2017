/* Created Tue Jan 31 12:13:01 MST 2017 */
package org.frc3019.robot;

import org.strongback.Strongback;
import org.strongback.SwitchReactor;
import org.strongback.components.Motor;
import org.strongback.components.ui.ContinuousRange;
import org.strongback.components.ui.Gamepad;
import org.strongback.drive.TankDrive;
import org.strongback.hardware.Hardware;
import org.strongback.components.Switch;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.frc3019.robot.Constants;
import org.frc3019.robot.subsystems.*;

public class Robot extends IterativeRobot {

	private DriveSystem driveSystem;
	private PickupSystem pickupSystem;
	private ContinuousRange speed;
	private ContinuousRange turn;
	private ContinuousRange pickupThrottle;
	private ContinuousRange climbThrottle;
	
	/*
	 * @see edu.wpi.first.wpilibj.IterativeRobot#robotInit()
	 * 
	 * Here we instantiate the basic susbsystems (things like motor, drive, and switch objects).
	 * 
	 */
    @Override
    public void robotInit() {
    	Strongback.configure().recordDataToFile(Constants.LOGFILE_PATH).recordEventsToFile(Constants.LOGFILE_PATH,2097152);
    
    	makeDriveSystem();
    	makePickup();
    	makeClimber();
    	makeShooter();
    	makeAgitator();
    	
    	makeJoystick();
    	SwitchReactor reactor = Strongback.switchReactor();
    	
    	//TODO: COMMANDS GO HERE
    	
    }
    /**
     * Creates the Joystick and the ranges for speed and turn.
     */
    private void makeJoystick() {
    	Gamepad joystick = Hardware.HumanInterfaceDevices.xbox360(Constants.XBOX1_PORT);
    	speed = joystick.getRightY().invert();
    	turn = joystick.getRightX();
    	pickupThrottle = joystick.getLeftTrigger();
    	climbThrottle = joystick.getRightTrigger();
    	Switch agitatorSwitch = joystick.getA();
    	Switch shooterSwitch = joystick.getB();
	}
    /**
     * Creates the Agitator Motor used in the Agitator Commands.
     */
	public void makeAgitator() {
		Motor agitatorMotor = Hardware.Motors.victorSP(Constants.AGITATOR_MOTOR_PWM);
	}
	/**
     * Creates the Agitator Motor used in the Agitator Commands.
     */
	private void makeShooter() {
		Motor shooterMotor = Hardware.Motors.victorSP(Constants.SHOOTER_MOTOR_PWM);
	}
	/**
     * Creates the Climber Motor used in the Climber Commands.
     */
	private void makeClimber() {
		Motor climberMotor = Hardware.Motors.victorSP(Constants.CLIMBER_MOTOR_PWM);
	}
	/**
     * Creates the Pickup Motor used in the Pickup Commands.
     */
	private void makePickup() {
		Motor pickupMotor = Hardware.Motors.victorSP(Constants.PICKUP_MOTOR_PWM);
	}
	/**
     * Creates the motors used in the drivetrain then assigns them to a driveSystem.
     */
	private void makeDriveSystem() {
    	Motor leftFrontMotor = Hardware.Motors.victorSP(Constants.LEFT_FRONT_MOTOR_PWM);
    	Motor leftRearMotor = Hardware.Motors.victorSP(Constants.LEFT_REAR_MOTOR_PWM);
    	Motor rightFrontMotor = Hardware.Motors.victorSP(Constants.RIGHT_FRONT_MOTOR_PWM);
    	Motor rightRearMotor = Hardware.Motors.victorSP(Constants.RIGHT_REAR_MOTOR_PWM);
    	Motor leftMotors = Motor.compose(leftFrontMotor, leftRearMotor);
    	Motor rightMotors = Motor.compose(rightFrontMotor, rightRearMotor);
    	TankDrive tankDrive = new TankDrive(leftMotors, rightMotors);
    	driveSystem = new DriveSystem(tankDrive);
	}
    

	@Override
    public void teleopInit() {
        // Start Strongback functions ...
        Strongback.restart();
    }

    @Override
    public void teleopPeriodic() {
    	
    	driveSystem.arcadeDrive(speed.read(), turn.read());
    	SmartDashboard.putNumber("speed value", speed.read());
    	SmartDashboard.putNumber("turn value", turn.read());
    	
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }

}
