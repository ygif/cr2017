package org.frc3019.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Constants {
	public static final String LOGFILE_PATH = "/home/lvuser/frc3019";

	// PWMS
	// DRIVE PWMS
	public static final int LEFT_FRONT_MOTOR_PWM = 0;
	public static final int LEFT_REAR_MOTOR_PWM = 1;
	public static final int RIGHT_FRONT_MOTOR_PWM = 3;
	public static final int RIGHT_REAR_MOTOR_PWM = 2;

	// MOTOR-DRIVEN SUBSYSTEM PWMS
	public static final int PICKUP_MOTOR_PWM = 4;
	public static final int SHOOTER_MOTOR_PWM = 5;
	public static final int CLIMBER_MOTOR_PWM = 6;
	public static final int AGITATOR_MOTOR_PWM = 7;

	// ROBOT CONSTANTS
	public static final double DRIVE_SCALE_FACTOR = 1.0;
	public static final double PICKUP_SCALE_FACTOR = 1.0;
	public static final double CLIMBSPEED_SCALE_FACTOR = 1.0;
	public static final double SHOOTSPEED_SCALE_FACTOR = 1.0;
	public static final double AGITATORSPEED_SCALE_FACTOR = 1.0;

	// OI CONSTANTS
	public static final int XBOX1_PORT = 1;

}
