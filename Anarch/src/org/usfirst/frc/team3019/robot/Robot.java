
package org.usfirst.frc.team3019.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

import java.io.IOException;

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
import org.usfirst.frc.team3019.robot.utilities.Playback;
import org.usfirst.frc.team3019.robot.utilities.Recorder;
import org.usfirst.frc.team3019.robot.utilities.SystemStates;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

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
	SendableChooser<String> station = new SendableChooser<String>();
	SendableChooser<Boolean> shouldRecord = new SendableChooser<Boolean>();
	SendableChooser<String> switchSide = new SendableChooser<String>();
	Recorder recorder;
	Playback auto;

	public Robot() {
		instantiateSubsystems();
		recorder = new Recorder(RobotMap.xbox1Port);
		
		station.addDefault("Left station", "left");
		station.addObject("Center station", "center");
		station.addObject("Right station", "right");
		SmartDashboard.putData("Alliance station", station);
		
		shouldRecord.addDefault("Don't record", Boolean.FALSE);
		shouldRecord.addObject("record", Boolean.TRUE);
		SmartDashboard.putData("Toggle Recorder", shouldRecord);
		
		switchSide.addDefault("Left", "L");
		switchSide.addObject("Right", "R");
		SmartDashboard.putData(switchSide);
	}

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

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
		recorder.stop();
		if(auto != null) {
			auto.stop();
			auto = null;
		}
		oi.xbox.setPlaybackMode(false);
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
		if(auto == null) {
			String name = DriverStation.getInstance().getGameSpecificMessage().substring(0, 1);
			name += station.getSelected();
			auto = new Playback(name);
		}
		auto.start();
		oi.xbox.setPlaybackMode(true);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		auto.playback();
		putTestInfo();
		SmartDashboard.putNumber("POV value", oi.xbox.getPOV());
		SmartDashboard.putString("Current Auto Command: ", String.valueOf(currentAutoCommand));
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if(auto != null) {
			auto.stop();
			auto = null;
		}
		oi.xbox.setPlaybackMode(false);
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

		if(shouldRecord.getSelected().booleanValue() && !recorder.isRunning) {
			recorder.start(switchSide.getSelected() + station.getSelected());
		} else if(shouldRecord.getSelected().booleanValue() == false && recorder.isRunning) {
			recorder.stop();
		}
		if(recorder.isRunning) {
			try {
				recorder.record();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		putTestInfo();
		SmartDashboard.putNumber("drivefactor", RobotMap.DRIVE_SCALE_FACTOR);
		SmartDashboard.putNumber("shooterSpeed", RobotMap.SHOOTSPEED_SCALE_FACTOR);
		SmartDashboard.putBoolean("Joystick", oi.shooterSwitch.get());
		SmartDashboard.putString("pickupState", pickupStates.toString());
		Scheduler.getInstance().run();
	}
	
	private void putTestInfo() {
		SmartDashboard.putString("left stick", oi.xbox.getX(Hand.kLeft) + " " + oi.xbox.getY(Hand.kLeft));
		SmartDashboard.putNumber("time", Timer.getFPGATimestamp());
		SmartDashboard.putString("buttons", Integer.toBinaryString(DriverStation.getInstance().getStickButtons(1)));
		SmartDashboard.putBoolean("Recorder on?", recorder.isRunning);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}
