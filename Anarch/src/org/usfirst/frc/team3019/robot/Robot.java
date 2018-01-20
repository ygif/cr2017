
package org.usfirst.frc.team3019.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3019.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team3019.robot.subsystems.AgitatorSystem;
import org.usfirst.frc.team3019.robot.subsystems.ClimberSystem;
import org.usfirst.frc.team3019.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3019.robot.subsystems.PickupSystem;
import org.usfirst.frc.team3019.robot.subsystems.ShooterSystem;
import org.usfirst.frc.team3019.robot.utilities.AutonomousMode;
import org.usfirst.frc.team3019.robot.utilities.CurrentAutoCommand;
import org.usfirst.frc.team3019.robot.utilities.PickupState;
import org.usfirst.frc.team3019.robot.utilities.SystemStates;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

// /c/home/lvuser/
public class Robot extends IterativeRobot {

	public static SystemStates pickupPowerState = SystemStates.OFF;
	public static PickupState pickupStates = PickupState.INTAKE;
	public static CurrentAutoCommand currentAutoCommand = CurrentAutoCommand.STOP;

	public static Drivetrain driveTrain;
	public static PickupSystem pickupSystem;
	public static ShooterSystem shooterSystem;
	public static ClimberSystem climberSystem;
	public static AgitatorSystem agitatorSystem;
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();
	NetworkTableInstance nti = NetworkTableInstance.create();
	NetworkTableEntry nte;

	public Robot() {
		instantiateSubsystems();
		// create the chooser with all auto options
		chooser.addDefault("DRIVEFWD", new AutonomousCommandGroup(AutonomousMode.DRIVEFWD));
		chooser.addObject("TENSHOTRED", new AutonomousCommandGroup(AutonomousMode.TENSHOTRED));
		chooser.addObject("TENSHOTBLUE", new AutonomousCommandGroup(AutonomousMode.TENSHOTBLUE));
		chooser.addObject("TESTAUTO", new AutonomousCommandGroup(AutonomousMode.TESTAUTOBLUE));
		chooser.addObject("GEARBLUE", new AutonomousCommandGroup(AutonomousMode.GEARBLUE));
		chooser.addObject("COMBOBLUE", new AutonomousCommandGroup(AutonomousMode.BLUECOMBO));
	}

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

		nti = NetworkTableInstance.create();
		nti.startServer();
		nte = new NetworkTableEntry(nti, NetworkTableEntry.kPersistent);
		nte.setDefaultDouble(0.0);

		/*new Thread(() -> {

			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);

			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);

			Mat source = new Mat();
			Mat output = new Mat();

			while (!Thread.interrupted()) {
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
				outputStream.putFrame(output);
			}

		}).start();*/
	}

	private void instantiateSubsystems() {
		driveTrain = new Drivetrain();
		pickupSystem = new PickupSystem();
		shooterSystem = new ShooterSystem();
		climberSystem = new ClimberSystem();
		agitatorSystem = new AgitatorSystem();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		putTestInfo();

		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putString("Current Auto Command: ", String.valueOf(currentAutoCommand));
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if (pickupPowerState == SystemStates.ON) {
			if (pickupStates == PickupState.OUTTAKE) {
				pickupSystem.reverseMotor();
			} else {
				pickupSystem.runMotor();
			}
		} else {
			pickupSystem.stopMotor();
		}

		putTestInfo();
		SmartDashboard.putNumber("drivefactor", RobotMap.DRIVE_SCALE_FACTOR);
		SmartDashboard.putNumber("shooterSpeed", RobotMap.SHOOTSPEED_SCALE_FACTOR);
		SmartDashboard.putBoolean("Joystick", oi.shooterSwitch.get());
		SmartDashboard.putString("pickupState", pickupStates.toString());
		Scheduler.getInstance().run();
	}
	
	private void putTestInfo() {
		SmartDashboard.putNumber("time", Timer.getFPGATimestamp());
		SmartDashboard.putString("buttons", Integer.toBinaryString(DriverStation.getInstance().getStickButtons(1)));
		SmartDashboard.putString("left axis", Double.toString(DriverStation.getInstance().getStickAxis(1, 0)) + " , "
				+ Double.toString(DriverStation.getInstance().getStickAxis(1, 1)));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
