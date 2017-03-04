package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.AutonomousMode;
import org.usfirst.frc.team3019.robot.utilities.CurrentAutoCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
    
    public  AutonomousCommandGroup(AutonomousMode mode) {
    	
    	if(mode == AutonomousMode.DRIVEFWD){  
    		addSequential(new Drive(1,0) , 1);
    		addSequential(new Drive(0,0) , 0.1);
    	}else if(mode == AutonomousMode.TENSHOT){
    		//drive
    		addParallel(new ShootWhile(),6);
    		Robot.currentAutoCommand = CurrentAutoCommand.DRIVEFWD;
    		addSequential(new Drive(0.8,0) , 1.2);
    		addSequential(new Drive(0,0) , 0.1);
    		//turn
    		Robot.currentAutoCommand = CurrentAutoCommand.TURN;
    		addSequential(new Drive(0,-1) , 0.5);
    		addSequential(new Drive(0,0) , 0.1);
    		//drivefwd
    		Robot.currentAutoCommand = CurrentAutoCommand.DRIVEFWD;
    		
    		addSequential(new Drive(0.6,0) , 1.4);
    		addSequential(new Drive(0,0) , 0.1);
    		//rev
    		Robot.currentAutoCommand = CurrentAutoCommand.REV;
//    		addSequential(new ShootWhile(),6);
    		//shoot
    		Robot.currentAutoCommand = CurrentAutoCommand.SHOOT;
    		addParallel(new ShootWhile(),5);
    		addParallel(new AgitateWhile(),5);
    	}else{
    		
    	}
    	
    	
     }
}
