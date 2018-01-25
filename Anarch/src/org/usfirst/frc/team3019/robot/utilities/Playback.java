package org.usfirst.frc.team3019.robot.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.usfirst.frc.team3019.robot.Robot;

public class Playback {
	
	private final String PATH = "/home/lvuser";
	private File file;
	private BufferedReader br;
	
	public Playback(String name) {
		file = new File(PATH + "/" + name + ".aif");
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		
	}
	
	public void playback() {
		String in = "";
		try {
			in = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(in != null) {
			String[] input = in.split("|");
			//set buttons
			{
				String[] tokens = input[0].split(" ");
				boolean[] buttonStates = new boolean[tokens.length];
				for(int i = 0; i < tokens.length; i++) {
					buttonStates[i] = tokens[i].equalsIgnoreCase("true");
				}
				Robot.oi.forceButtons(buttonStates);
			}
			//set axes
			{
				String[] tokens = input[1].split(" ");
				
			}
			//set POV buttons
			{
				String[] tokens = input[2].split(" ");
				
			}
		}
	}
	
	public void stop() {
		Robot.oi.forceButtons(new boolean[] {
			false, false, false, false, false, false, false, false, false, false		
		});
	}
}
