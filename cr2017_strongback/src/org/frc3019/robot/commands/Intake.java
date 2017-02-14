package org.frc3019.robot.commands;

import org.frc3019.robot.Robot;
import org.frc3019.robot.subsystems.PickupSystem;
import org.frc3019.robot.util.PickupState;
import org.frc3019.robot.util.SystemStates;
import org.strongback.command.Command;
import org.strongback.components.Switch;

public class Intake extends Command {
	private PickupSystem sys;
	private Switch sw;

	public Intake(PickupSystem system, Switch tSwitch) {
		super(system);
		sys = system;
		sw = tSwitch;
	}
	// @Override
	// public void initialize(){
	//
	// }

	@Override
	public boolean execute() {
		if (Robot.pickupPowerState == SystemStates.ON) {
			Robot.pickupPowerState = SystemStates.OFF;
		} else {
			Robot.pickupPowerState = SystemStates.ON;
		}
		return true;
	}
}
