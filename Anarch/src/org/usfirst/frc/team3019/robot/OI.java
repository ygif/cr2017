package org.usfirst.frc.team3019.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3019.robot.commands.AgitateWhile;
import org.usfirst.frc.team3019.robot.commands.Climb;
import org.usfirst.frc.team3019.robot.commands.Drive;
import org.usfirst.frc.team3019.robot.commands.ShootWhile;
import org.usfirst.frc.team3019.robot.utilites.PickupState;
import org.usfirst.frc.team3019.robot.utilites.SystemStates;

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
	public Joystick xbox = new Joystick(RobotMap.xbox1Port);
	
	Button pickupThrottle = new JoystickButton(xbox, 2);
	Button climbThrottle = new JoystickButton(xbox, 6);
	Button agitatorSwitch = new JoystickButton(xbox, 1);
	Button shooterSwitch = new JoystickButton(xbox, 3);
	Button pickupPowerSwitch = new JoystickButton(xbox, 4);
	
	public OI(){
		pickupThrottle.whileHeld(new Command(){

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
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		pickupPowerSwitch.whileHeld(new Command(){

			
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
				// TODO Auto-generated method stub
				return false;
			}
			
		});
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
