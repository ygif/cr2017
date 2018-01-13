package org.usfirst.frc.team3019.robot.commands;

import org.usfirst.frc.team3019.robot.Robot;
import org.usfirst.frc.team3019.robot.utilities.AutonomousMode;
import org.usfirst.frc.team3019.robot.utilities.CurrentAutoCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
    
    public  AutonomousCommandGroup(AutonomousMode mode) {
    	
    	if(mode == AutonomousMode.DRIVEFWD){  
    		addSequential(new Drive(1,0) , 1);
    		addSequential(new Drive(0,0) , 0.1);
    	}else if(mode == AutonomousMode.TENSHOTRED){
    		//drive
    		addParallel(new ShootWhile(0.4255),6);
    		Robot.currentAutoCommand = CurrentAutoCommand.DRIVEFWD;
    		addSequential(new Drive(0.79,0) , 1.4);
    		addSequential(new Drive(0,0) , 0.3);
    		//turn
    		Robot.currentAutoCommand = CurrentAutoCommand.TURN;
    		addSequential(new Drive(0,-0.865) , 0.613);
    		addSequential(new Drive(0,0) , 0.3);
    		//drivefwd
    		Robot.currentAutoCommand = CurrentAutoCommand.DRIVEFWD;
    		
    		addSequential(new Drive(0.65,0) , 1.5);
    		addSequential(new Drive(0,0) , 0.3);
    		//rev
    		Robot.currentAutoCommand = CurrentAutoCommand.REV;
//    		addSequential(new ShootWhile(),6);
    		//shoot
    		Robot.currentAutoCommand = CurrentAutoCommand.SHOOT;
    		addParallel(new ShootWhile(0.4255),11);
    		addParallel(new AgitateWhile(),11);
    	}else if(mode == AutonomousMode.TENSHOTBLUE){
    		//drive
    		addParallel(new ShootWhile(0.4255),6);
    		Robot.currentAutoCommand = CurrentAutoCommand.DRIVEFWD;
    		addSequential(new Drive(0.79,0) , 1.4);
    		addSequential(new Drive(0,0) , 0.3);
    		//turn
    		Robot.currentAutoCommand = CurrentAutoCommand.TURN;
    		addSequential(new Drive(0,0.865) , 0.613);
    		addSequential(new Drive(0,0) , 0.3);
    		//drivefwd
    		Robot.currentAutoCommand = CurrentAutoCommand.DRIVEFWD;
    		
    		addSequential(new Drive(0.65,0) , 1.5);
    		addSequential(new Drive(0,0) , 0.3);
    		//rev
    		Robot.currentAutoCommand = CurrentAutoCommand.REV;
//    		addSequential(new ShootWhile(),6);
    		//shoot
    		Robot.currentAutoCommand = CurrentAutoCommand.SHOOT;
    		addParallel(new ShootWhile(0.4255),11);
    		addParallel(new AgitateWhile(),11);
    	}else if(mode==AutonomousMode.TESTAUTOBLUE){
    		double shootSpeed = 0.4255;
    		double turnSpeed = 0.870;
    		
    		//rev
    		addParallel(new ShootWhile(shootSpeed),25);
    		addSequential(new Command() {
				@Override
				protected void initialize() {
					// TODO Auto-generated method stub
					SmartDashboard.putString("isDone", "notDone");
				}
				@Override
				protected boolean isFinished() {
					// TODO Auto-generated method stub
					return true;
				}
			});
    		//drive
    		addSequential(new Drive(0.75,0) , 1.4);
    		addSequential(new Drive(0,0),0.3);
    		//turn
    		addSequential(new Drive(0,turnSpeed),0.853);
    		addSequential(new Drive(0,0), 0.3);
    		//shoot
    		addSequential(new AgitateWhile(),11);
    		addSequential(new Command() {
				@Override
				protected void initialize() {
					// TODO Auto-generated method stub
					SmartDashboard.putString("isDone", "Done");
				}
				@Override
				protected boolean isFinished() {
					// TODO Auto-generated method stub
					return true;
				}
			});
    	}else if(mode == AutonomousMode.BLUECOMBO){
    		//double shootSpeed = 0.4255;
    		addSequential(new Drive(-0.80,0.4),1.7);
    		addSequential(new Drive(0,0),0.3);
    		
    		addSequential(new Drive(-0.75,-0.97),0.5);
    		addSequential(new Drive(0,0),0.3);
    		
    		addSequential(new Drive(-0.80,0.33),0.55);
    		addSequential(new Drive(0,0),3);
//    		
//    		
    		addSequential(new Drive(0.8,0),1.4);
    		addSequential(new Drive(0,0),.3);
    		addParallel(new ShootWhile(0.4255), 15);
    		addSequential(new AgitateWhile(),10);
    		

    	}else if(mode == AutonomousMode.GEARBLUE){
    		addSequential(new Drive(-0.80,0.4),1.7);
    		addSequential(new Drive(0,0),0.3);
    		
    		addSequential(new Drive(-0.75,-0.97),0.5);
    		addSequential(new Drive(0,0),0.3);
    		
    		addSequential(new Drive(-0.80,0.33),0.55);
    		addSequential(new Drive(0,0),0.3);
    		
    	}
    	
    	
     }
}
