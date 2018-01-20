package org.usfirst.frc.team3019.robot.utilities;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class PlaybackButton extends JoystickButton{
	
	private boolean isActive;

	public PlaybackButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
		isActive = false;
	}
	
	@Override
	public boolean get() {
		return isActive || super.get();
	}
	
	public void setActive(boolean b) {
		isActive = b;
	}
}
