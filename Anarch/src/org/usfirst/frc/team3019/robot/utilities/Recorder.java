package org.usfirst.frc.team3019.robot.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;

public class Recorder {

	private DriverStation ds;
	private int joystick;
	private int numOfButtons;
	private int numOfAxes;
	private int numOfPOV;
	private String path;
	private File file;
	boolean isRunning;

	public Recorder(int joystick) {
		ds = DriverStation.getInstance();
		this.joystick = joystick;
		numOfButtons = ds.getStickButtonCount(this.joystick);
		numOfAxes = ds.getStickAxisCount(this.joystick);
		numOfPOV = ds.getStickPOVCount(joystick);
		path = "/home/lvuser";
		isRunning = false;
	}

	void start(String name) {
		file = new File(path + "/" + name + ".aif");
		file.setWritable(true);
		file.setReadable(true);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		isRunning = true;
	}

	// /home/lvuser/
	void record() throws IOException {
		if (!isRunning) {
			if (file == null || !file.exists()) {
				throw new FileNotFoundException("start() should called before record.");
			}
			StringBuilder sb = new StringBuilder();
			// record button states to a string
			for (int i = 1; i <= numOfButtons; i++) {
				sb.append(ds.getStickButton(joystick, i) + " ");
			}
			// record axis values
			for (int i = 0; i < numOfAxes; i++) {
				sb.append(ds.getStickAxis(joystick, i) + " ");
			}
			// record POV button states
			for (int i = 0; i < numOfPOV; i++) {
				sb.append(ds.getStickPOV(joystick, i) + " ");
			}

			FileWriter fw = new FileWriter(file);
			fw.write(sb.toString() + "\n");
			fw.close();
		}
	}
	
	void stop() {
		file = null;
		isRunning = false;
	}
}
