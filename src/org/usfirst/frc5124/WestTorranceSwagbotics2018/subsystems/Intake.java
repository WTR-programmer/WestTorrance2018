package org.usfirst.frc5124.WestTorranceSwagbotics2018.subsystems;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.*;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {
	public boolean intaking;
	private final SpeedController intakeLeft = RobotMap.intakeLeft;
	private final SpeedController intakeRight = RobotMap.intakeRight;
	private final DoubleSolenoid clamp1 = RobotMap.clamp1;
	private final DoubleSolenoid clamp2 = RobotMap.clamp2;
	public final AnalogPotentiometer pot = RobotMap.pot;
	public final PIDController flipPID = new PIDController( 4, 0, 0, pot, RobotMap.flipper);
	
    public void initDefaultCommand() {
    	flipPID.enable();
        flipPID.setInputRange(.6, .9);
    }
    
    
    
    public void intake() {
    	intakeRight.set(-.5);
    	intakeLeft.set(-.5);
    }
    
    public void outtake() {
    	intakeRight.set(.8);
    	intakeLeft.set(.8);
    }
    public void intakePower(double speed) {
    	intakeRight.set(speed);
    	intakeLeft.set(speed);
    }
    
    public void disable() {
    	intakeRight.set(0);
    	intakeLeft.set(0);
    }
    public void Grab() {
    	Robot.intake.clamp1.set(Value.kForward);
    	Robot.intake.clamp2.set(Value.kReverse);
    }
    public void Ungrab() {
    	Robot.intake.clamp1.set(Value.kReverse);
    	Robot.intake.clamp2.set(Value.kReverse);
    	}
    /*
    public void setFlipperPID(double p, double I, double D) {
    	flipPID.setP(p);
    	flipPID.setI(I);
    	flipPID.setD(D);
    }
    */
    public void setFlipperSetpoint(double setpoint) {
    	flipPID.setSetpoint(setpoint);
    }
    public void setPIDCoeff(double p, double i , double d) {
    	flipPID.setPID(p, i, d);
    }
    /*
    public double getPIDSetpoint() {
    	return flipPID.getSetpoint();
    }
    
    public double getPID() {
    	return flipPID.get();
    }
    
    public void setPIDCoeff(double p, double i , double d) {
    	flipPID.setPID(p, i, d);
    }
    */
    public double[] getPIDCoeff() {
    	double[] a = {flipPID.getP(), flipPID.getI(), flipPID.getD()};
    	return a;
    }   
    
    public double getPot() {
    	return RobotMap.pot.get();
    }
    
    public void setFlipperPower(double power) {
    	RobotMap.flipper.set(power);
    }
    public void enablePID() {
    	flipPID.enable();
    }
    public void disablePID() {
    	flipPID.disable();
    }
    
    public void setThePIDs() {
    	double p = SmartDashboard.getNumber("set P", Robot.intake.getPIDCoeff()[0]);
    	double i = SmartDashboard.getNumber("set I", Robot.intake.getPIDCoeff()[1]);
    	double d = SmartDashboard.getNumber("set D", Robot.intake.getPIDCoeff()[2]);
    	Robot.intake.setPIDCoeff(p, i, d);
    }
    public boolean PIDactive() {
    	return flipPID.isEnabled();
    }
    
}

