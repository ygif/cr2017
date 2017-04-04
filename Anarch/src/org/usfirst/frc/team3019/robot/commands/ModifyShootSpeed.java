package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ModifyShootSpeed extends Command {
	//true = up
	//false = down
	boolean direction;
	
    public ModifyShootSpeed(boolean x) {
        // Use requires() here to declare subsystem dependencies
        super(); 
    	requires(Robot.shooterSystem);
    	direction = x;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(direction){
    		RobotMap.SHOOTSPEED_SCALE_FACTOR += 0.005;
    	}else{
    		RobotMap.SHOOTSPEED_SCALE_FACTOR -= 0.005;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
