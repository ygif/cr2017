/* Created Tue Jan 31 12:13:01 MST 2017 */
package org.frc3019.robot;

import org.junit.Test;
import org.strongback.Strongback;
import org.strongback.command.CommandTester;
import org.strongback.mock.Mock;
import org.strongback.mock.MockMotor;
import org.strongback.mock.MockSwitch;
import org.frc3019.robot.commands.Intake;
import org.frc3019.robot.commands.ToggleIntake;
import org.frc3019.robot.subsystems.PickupSystem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class TestPickup {

	PickupSystem ps;
	Intake in;
	ToggleIntake ti;
	MockSwitch ms;
	
	@Before
	public void init() {
		Strongback.start();
		
		ms = Mock.notTriggeredSwitch();
		
		MockMotor m1 = Mock.stoppedMotor();
		ps = new PickupSystem(m1);
		
		in = new Intake(ps, ms);
		ti = new ToggleIntake(ps);
	}
	
    @Test
    public void test() {
    	
    }
    
    @After
    public void endTest() {
    	Strongback.stop();
    }
}
