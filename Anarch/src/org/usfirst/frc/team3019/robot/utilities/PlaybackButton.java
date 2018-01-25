package org.usfirst.frc.team3019.robot.utilities;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class PlaybackButton extends JoystickButton{
	
	private boolean forceActive;
	private final int butnum;

	public PlaybackButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
		butnum = buttonNumber;
		forceActive = false;
	}
	
	public int getNum() {
		return butnum;
	}
	
	@Override
	public boolean get() {
		return forceActive || super.get();
	}
	
	/**
	 * Manual control over the button's state
	 * 
	 * @param b whether the button should be active
	 */
	public void setActive(boolean b) {
		forceActive = b;
	}
}
