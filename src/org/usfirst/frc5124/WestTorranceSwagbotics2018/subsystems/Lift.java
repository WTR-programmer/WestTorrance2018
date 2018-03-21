package org.usfirst.frc5124.WestTorranceSwagbotics2018.subsystems;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Lift extends Subsystem {

	
	
	private final SpeedControllerGroup liftSpeedController = RobotMap.liftSpeed;
	private final Encoder liftEncoder = RobotMap.liftEncoder;
	
    public void initDefaultCommand() {
    }
    
    public void periodic() {
    }
    
   
    public void brake() {
    	RobotMap.brake.set(Value.kReverse);
    }
    public void Unbrake() {
    	RobotMap.brake.set(Value.kForward);
    }
    public void liftUp() {
    	Unbrake();
    	liftSpeedController.set(.5);
    }
    public void liftDown() {
    	Unbrake();
    	liftSpeedController.set(-.2);
    }
    public void MegaLiftDown() {
    	Unbrake();
    	liftSpeedController.set(-1);
    }
    
    public void liftLock() {
    	liftSpeedController.set(0);
    	brake();
    }
    public boolean isDown() {
    	return !RobotMap.liftLimitSwitch.get();
    }
    public int getEncoder() {
    	return liftEncoder.getRaw();
    }
    
}
