package org.frc3019.robot.commands;

import org.frc3019.robot.subsystems.ShooterSystem;
import org.strongback.command.Command;
import org.strongback.components.Switch;

public class ShootWhile extends Command{
	private ShooterSystem shootSys;
	private Switch shooterSwitch;
	
	
	public ShootWhile(ShooterSystem sys, Switch shootSwitch) {
		super(sys);
		shootSys = sys;
		shooterSwitch = shootSwitch;
	}
	
	@Override
	public boolean execute() {
		shootSys.startShooter();
		return !shooterSwitch.isTriggered();
	}
	public void end(){
		shootSys.stopShooter();
	}

}
