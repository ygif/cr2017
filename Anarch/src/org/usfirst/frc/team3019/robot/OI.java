package org.usfirst.frc.team3019.robot;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3019.robot.commands.AgitateWhile;
import org.usfirst.frc.team3019.robot.commands.Climb;
import org.usfirst.frc.team3019.robot.commands.ModifyShootSpeed;
import org.usfirst.frc.team3019.robot.commands.ShootWhile;
import org.usfirst.frc.team3019.robot.utilities.PlaybackButton;
import org.usfirst.frc.team3019.robot.utilities.PlaybackXboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	// public Joystick xbox = new Joystick(RobotMap.xbox1Port);
	public PlaybackXboxController xbox = new PlaybackXboxController(RobotMap.xbox1Port);

	PlaybackButton pickupThrottle = new PlaybackButton(xbox, 2);
	PlaybackButton climbThrottle = new PlaybackButton(xbox, 6);//should be 6
	PlaybackButton agitatorSwitch = new PlaybackButton(xbox, 1);
	PlaybackButton shooterSwitch = new PlaybackButton(xbox, 3);
	PlaybackButton pickupPowerSwitch = new PlaybackButton(xbox, 4);
	PlaybackButton shootSpeedUpSwitch = new PlaybackButton(xbox, 8);
	PlaybackButton shootSpeedDownSwitch = new PlaybackButton(xbox, 7);
	PlaybackButton fullpowerShot = new PlaybackButton(xbox, 9);
	PlaybackButton nerfButton = new PlaybackButton(xbox, 10);
	PlaybackButton toggleDriveOrientation = new PlaybackButton(xbox, 5);

	public OI() {
		toggleDriveOrientation.whenPressed(new Command() {
			@Override
			protected void initialize() {
				RobotMap.orientForward = !RobotMap.orientForward;
			}

			@Override
			protected boolean isFinished() {
				return true;
			}
		});
		fullpowerShot.whileHeld(new Command() {
			@Override
			protected void execute() {
				RobotMap.SHOOTSPEED_SCALE_FACTOR = 1;
			}

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void end() {
				RobotMap.SHOOTSPEED_SCALE_FACTOR = 0.43;
			}
		});
		nerfButton.whileHeld(new Command() {

			@Override
			protected boolean isFinished() {
				return false;
			}

			@Override
			protected void execute() {
				RobotMap.DRIVE_SCALE_FACTOR = 0.5;

			}

			@Override
			protected void end() {
				RobotMap.DRIVE_SCALE_FACTOR = 1;
			}
		});
		pickupThrottle.whileHeld(new Command() {

			@Override
			protected void execute() {
				Robot.pickupSystem.reverseMotor();
			}

			@Override
			protected void end() {
				Robot.pickupSystem.stopMotor();
			}

			@Override
			protected boolean isFinished() {
				return false;
			}

		});
		pickupPowerSwitch.whileHeld(new Command() {

			@Override
			protected void execute() {
				Robot.pickupSystem.runMotor();
			}

			@Override
			protected void end() {
				Robot.pickupSystem.stopMotor();
			}

			@Override
			protected boolean isFinished() {
				return false;
			}

		});
		shootSpeedUpSwitch.whenPressed(new ModifyShootSpeed(true));
		shootSpeedDownSwitch.whenPressed(new ModifyShootSpeed(false));
		agitatorSwitch.whileHeld(new AgitateWhile());
		shooterSwitch.whileHeld(new ShootWhile());
		climbThrottle.whileHeld(new Climb());
	}

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
