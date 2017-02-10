package org.frc3019.robot.commands;

import org.frc3019.robot.Robot;
import org.frc3019.robot.subsystems.PickupSystem;
import org.frc3019.robot.util.PickupState;
import org.strongback.command.Command;
import org.strongback.components.Switch;

public class ToggleIntake extends Command{
	
	public ToggleIntake(PickupSystem sys) {
		super(sys);
	}
	
	@Override
	public boolean execute() {
		if(Robot.pickupStates == PickupState.INTAKE){
			Robot.pickupStates = PickupState.OUTTAKE;
		}else if(Robot.pickupStates == PickupState.OUTTAKE){
			Robot.pickupStates = PickupState.INTAKE;
		}
		return true;
	}

}
