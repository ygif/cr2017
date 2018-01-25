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
	private final String PATH = "/home/lvuser";
	private File file;
	public boolean isRunning;

	/**
	 * 
	 * @param joystick joystick port number
	 */
	public Recorder(int joystick) {
		ds = DriverStation.getInstance();
		this.joystick = joystick;
		numOfButtons = ds.getStickButtonCount(this.joystick);
		numOfAxes = ds.getStickAxisCount(this.joystick);
		numOfPOV = ds.getStickPOVCount(joystick);
		isRunning = false;
	}

	/**
	 * Start recording input data from a joystick
	 * 
	 * @param name The name of the file to write to.
	 */
	public void start(String name) {
		file = new File(PATH + "/" + name + ".aif");
		file.setWritable(true);
		file.setReadable(true);

		if (file.exists()) {
			file.delete();
		}

		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		isRunning = true;
	}

	// /home/lvuser/
	/**
	 * Takes the state of every button and axis and writes those to a file.
	 * 
	 * @throws IOException
	 */
	public void record() throws IOException {
		if (file == null || !file.exists()) {
			throw new FileNotFoundException("start() should called before record.");
		}

		StringBuilder sb = new StringBuilder();
		// record button states to a string
		for (int i = 1; i <= numOfButtons; i++) {
			sb.append(ds.getStickButton(joystick, i) + " ");
		}

		sb.append('|');
		// record axis values
		for (int i = 0; i < numOfAxes; i++) {
			sb.append(ds.getStickAxis(joystick, i) + " ");
		}

		sb.append('|');
		// record POV button states
		for (int i = 0; i < numOfPOV; i++) {
			sb.append(ds.getStickPOV(joystick, i) + " ");
		}

		FileWriter fw = new FileWriter(file);
		fw.write(sb.toString() + "\n");
		fw.close();
	}

	public void stop() {
		file = null;
		isRunning = false;
	}
}
