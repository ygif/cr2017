package org.frc3019.robot.commands;

import org.frc3019.robot.subsystems.AgitatorSystem;
import org.strongback.command.Command;
import org.strongback.components.Switch;

public class AgitateWhile extends Command {
	private AgitatorSystem agi;
	private Switch agiSwitch;

	public AgitateWhile(AgitatorSystem agi, Switch agiSwitch) {
		super(agi);
		this.agi = agi;
		this.agiSwitch = agiSwitch;
	}

	@Override
	public boolean execute() {
		agi.runAgitator();
		return !agiSwitch.isTriggered();
	}

	public void end() {
		agi.stopAgitator();
	}

}
