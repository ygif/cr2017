package org.usfirst.frc.team3019.robot.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.usfirst.frc.team3019.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Playback {
	
	private final String PATH = "/home/lvuser";
	private File file;
	private BufferedReader br;
	public boolean isRunning;
	private boolean atEndOfFile;
	
	public Playback(String name) {
		file = new File(PATH + "/" + name + ".txt");
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		isRunning = false;
		atEndOfFile = false;
	}
	
	public Playback() {
		file = new File(PATH + "/temp.txt");
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	public void start() {
		isRunning = true;
	}
	
	public void playback() {
		String in = "";
		try {
			in = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(in != null) {
			String[] input = in.split(";", 3);
			//set buttons
			{
				String[] tokens = input[0].split(" ");
				boolean[] buttonStates = new boolean[tokens.length];
				for(int i = 0; i < tokens.length; i++) {
					buttonStates[i] = tokens[i].equalsIgnoreCase("true");
				}
				//Robot.oi.forceButtons(buttonStates);
				Robot.oi.xbox.setButtonValues(buttonStates);
			}
			//set axes
			{
				String[] tokens = input[1].split(" ");
				double[] axisValues = new double[tokens.length];
				for (int i = 0; i < tokens.length; i++) {
					axisValues[i] = Double.parseDouble(tokens[i]);
				}
				Robot.oi.xbox.setAxisValues(axisValues);
			}
			//set POV buttons
			{
				String[] tokens = input[2].split(" ");
				int[] povValues = new int[tokens.length];
				for (int i = 0; i < tokens.length; i++) {
					povValues[i] = Integer.parseInt(tokens[i]);
				}
				Robot.oi.xbox.setPOVValues(povValues);
			}
		} else if(atEndOfFile == false) {
			atEndOfFile = true;
			System.out.println("Reached the end of the recording.");
		}
	}
	
	public void stop() {
		Robot.oi.xbox.setButtonValues(new boolean[Robot.oi.xbox.getButtonCount()]);
		//Robot.oi.forceButtons(new boolean[Robot.oi.xbox.getButtonCount()]);
		Robot.oi.xbox.setAxisValues(new double[Robot.oi.xbox.getAxisCount()]);
		Robot.oi.xbox.setPOVValues(new int[Robot.oi.xbox.getPOVCount()]);
		isRunning = false;
	}
}
