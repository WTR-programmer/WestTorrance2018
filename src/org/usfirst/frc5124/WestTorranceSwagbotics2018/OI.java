package org.usfirst.frc5124.WestTorranceSwagbotics2018;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {

	public Joystick driver;
	public XboxController operator;
	public Joystick logitech;
	public JoystickButton intakeButton;
	public JoystickButton outtakeButton;


	public OI() {
    	
    	driver = new Joystick(0);
    	operator = new XboxController(1);
    	logitech = new Joystick(2);
    	intakeButton = new JoystickButton(operator, 10);
    	outtakeButton = new JoystickButton(operator, 11);
    	
    	//intakeButton.whileHeld(new IntakeConsume());
    	
    }
    
    public Joystick getDriver() {
    		return driver;
    }
    
    public XboxController getOperator() {
    		return operator;
    }
    public Joystick logitech() {
    	return logitech;
    }
}

