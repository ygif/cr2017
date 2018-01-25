package org.usfirst.frc.team3019.robot.utilities;

import edu.wpi.first.wpilibj.XboxController;

public class PlaybackXboxController extends XboxController{
	
	private double[] macroAxisValues;
	private int[] macroPOVValues;
	private boolean playback = false;

	public PlaybackXboxController(int port) {
		super(port);
		macroAxisValues = new double[super.getAxisCount()];
		macroPOVValues = new int[super.getPOVCount()];
	}
	
	public void setAxisValues(double[] values) {
		for (int i = 0; i < values.length; i++) {
			macroAxisValues[i] = values[i];
		}
	}
	
	public void setPOVValues(int[] values) {
		for (int i = 0; i < values.length; i++) {
			macroPOVValues[i] = values[i];
		}
	}
	
	public void setPlaybackMode(boolean mode) {
		playback = mode;
	}
	
	@Override
	public double getRawAxis(int axis) {
		return (playback) ? macroAxisValues[axis] : super.getRawAxis(axis);
	}
	
	@Override
	public int getPOV(int pov) {
		return (playback) ? macroPOVValues[pov] : super.getPOV(pov);
	}
}
