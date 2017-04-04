package org.usfirst.frc.team3019.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static double DRIVE_SCALE_FACTOR = 1.0;
	public static final double PICKUP_SCALE_FACTOR = 1.0;
	public static final double CLIMBSPEED_SCALE_FACTOR = 1.0;
	public static double SHOOTSPEED_SCALE_FACTOR = 0.43;
	public static final double AGITATORSPEED_SCALE_FACTOR = -0.65;
	
	public static int leftFrontMotor = 0;
	public static int leftRearMotor = 1;
	public static int rightFrontMotor = 3;
	public static int rightRearMotor = 2;
	
	public static int pickupMotor = 4;
	public static int shooterMotor = 5;
	public static int climberMotor = 6;
	public static int agitatorMotor = 7;
	
	public static int xbox1Port = 1;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static boolean orientForward = true;
}
