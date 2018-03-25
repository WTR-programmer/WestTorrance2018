 package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class DriveTrainJoystickPuppetry extends Command {

	
	private boolean intaking;
	private boolean outtaking;
	private boolean grabbing;
	private boolean releasing;
	private boolean lifting;
	private boolean lowering;
	
	private double liftPower;
	
	private double left;
	private double right;
	private double powerMultiplier;
	
	private double Lowest_Flip = .61;
	private double Highest_Flip = 1.3;
	private double Middle_Flip = .8;
	
	private double safe_Flip = .78;
	private double low_Flip = .7;								
	private double liftDown;
	private double liftUp;
	private double liftMid;
	private double intakePower;
	private double outtakePower;
	
	private double power;
	private double direction;
	
	boolean D_Up;
	boolean D_Down;
	boolean MidButton;
	boolean D_left;
	boolean D_right;
	
	boolean startActive;
	
	
	boolean flipActive;
	
	boolean LiftUp;
	boolean LiftDown;
	boolean LiftMid;
	
	double LiftHighPos = 0;
	double LiftMidPos = 0;
	double LiftDownPos = 0;
	
	double CurrentLift;
	
	Encoder LiftEncoder = RobotMap.liftEncoder;
    public DriveTrainJoystickPuppetry() {

        requires(Robot.driveTrain);
    }

    protected void initialize() {
    	intaking = false;
    	outtaking = false;
    	Robot.intake.disablePID();
    	RobotMap.driveEncoder.reset();

    	RobotMap.driveTrainRight1.setInverted(false);
    	RobotMap.driveTrainRight2.setInverted(false);
    	intakePower = -.8;
    	outtakePower = .8;

    	D_Up = false;
    	D_Down = false;
    	D_left = false;
    	D_right = false;
    	
    	startActive = false;
    	
    	flipActive = true;
    	Robot.driveTrain.DrivePID.disable();
    	Robot.driveTrain.TurnPID.disable();
    }

    protected void execute() {
    	CurrentLift = RobotMap.liftEncoder.get();
    	//RobotMap.EntireDrive.disable();
    	//Drive Train
    	//slow button combo
    	Robot.driveTrain.DrivePID.disable();
    	Robot.driveTrain.TurnPID.disable();
//    	Robot.driveTrain.AdjustPID.disable();
//    	Robot.driveTrain.PowerPID.disable();

    	RobotMap.driveTrainRight1.setInverted(false);
    	RobotMap.driveTrainRight2.setInverted(false);
    	Robot.driveTrain.DrivePID.disable();
    	
    	power = -Robot.oi.getDriver().getY() * 1;
    	double x = Robot.oi.getDriver().getX() * 1;
    	double z = Robot.oi.getDriver().getZ() * 1;
    	if(Math.abs(power) < 0.1) {
    		power = 0;
    	}
    	if(Math.abs(x) < 0.1) {
    		x = 0;
    	}
    	direction = Math.abs(x) > Math.abs(z) ? x : z;
    	Robot.driveTrain.arcadeDrive(power, direction);

    	//Toggle D Pad
    	if (Robot.oi.operator.getPOV() == 0) {
    		D_Up = true;
    		D_Down = false;
    		MidButton = false;
    	}
    	if (Robot.oi.operator.getPOV() == 180) {
    		D_Down = true;
    		D_Up = false;
    		MidButton = false;
    	}
    	if (Robot.oi.operator.getRawButton(3)) {
    		MidButton = true;
    		D_Down = false;
    		D_Up = false;
    	}
    	
    	//flipper
    	
		if(D_Up) {
    		Robot.intake.setFlipperSetpoint(Middle_Flip);
    	} else if (D_Down) {
    		Robot.intake.setFlipperSetpoint(Lowest_Flip);
    	}
    	else if (MidButton) {
    		Robot.intake.setFlipperSetpoint(Highest_Flip);
    	}
    	
    	
    	
    	
    	
    	//disable PID Toggle
    	if (Robot.oi.operator.getRawButton(8) && startActive) {
    		flipActive = false;
    		startActive = false;
    	}
    	else if (Robot.oi.operator.getRawButton(8) && !startActive) {
    		flipActive = true;
    		startActive = true;
    	}
    	
    	//Disable PID Button
    	if (flipActive) {
    		Robot.intake.enablePID();
    	}
    	else {
    		Robot.intake.disablePID();
    	}
    	
    	
    	//intake
    	if (Robot.oi.operator.getRawAxis(2) > .55) {
    		Robot.intake.intakePower(intakePower);
    		intaking = true;
    	}    	else {
    		intaking = false;
    	}
    	
    	//for testing one intake motor at a time
    	/*if (Robot.oi.operator.getRawButton(10)) {
    		//RobotMap.intakeLeft.set(1);
    		RobotMap.intakeRight.set(1);
    		intaking = true;
    	}
    	if (Robot.oi.operator.getRawButton(9)) {
    		RobotMap.intakeLeft.set(1);
    		
    		intaking = true;
    	}*/
    	
    	
    	//outTake
    	if (Robot.oi.operator.getRawAxis(3) > .55) {
    		Robot.intake.intakePower(outtakePower);
    		outtaking = true;
    	} else {
    		outtaking = false;
    	}
    	
    	if (Robot.oi.operator.getRawButton(2)) {
    		outtakePower = .4;
    	}
    	else {
    		outtakePower = .8;
    	}
    	
    	//Turn Intake off if nothing is pressed
    	if (!intaking && !outtaking) {
    		Robot.intake.disable();
    	}
    	
    	
    	
    	
    	//Grab
    	if (Robot.oi.operator.getRawButtonPressed(6)) {
    		Robot.intake.Ungrab();
    		grabbing = true;
    	}
    	else {
    		grabbing = false;
    	}
    	//Ungrab
    	if (Robot.oi.operator.getRawButtonPressed(5)) {
    		Robot.intake.Grab();
    		releasing = true;
    	}
    	else {
    		releasing = false;
    	}
    	
    	
    	
    	
    	
    	
    	//lift
    	if (Robot.oi.operator.getPOV(0) == 90) {
    		Robot.Lift.Unbrake();
    		RobotMap.liftSpeed.set(.77);
    		lifting = true;
    		LiftUp = false;
    		LiftDown = false;
    	}
    	else {
    		lifting = false;
    		//RobotMap.liftSpeed.set(0);
    	}
    		
    	if (Robot.oi.operator.getPOV(0) == 270 && !Robot.Lift.isDown()) {
    		Robot.Lift.Unbrake();
    		RobotMap.liftSpeed.set(-.2);
    		lowering = true;
    		LiftUp = false;
    		LiftDown = false;
    	}
    	else {
    		lowering = false;
    	}
    	
    	if (Robot.oi.operator.getRawButton(7) && !Robot.Lift.isDown()) {
    		Robot.Lift.MegaLiftDown();
    		lowering = true;
    	}
    	
    	
    	//Brake Lift if nothing is pressed
    	if (!lifting && !lowering) {
    		RobotMap.liftSpeed.set(0);
    		Robot.Lift.brake();
    	}
    	
    	 //Lift Toggles
//    	if (Robot.oi.operator.getRawButton(4)) {
//    		LiftUp = true;
//    		LiftDown = false;
//    	}
//    	
//    	
//    	if (Robot.oi.operator.getRawButton(1)) {
//    		LiftDown = true;
//    		LiftUp = false;
//    	}
    	
    	//Using Lift Toggles
    	if (LiftUp) {
    		if (CurrentLift < LiftHighPos +- 10) {
    			Robot.Lift.liftUp();
    			lifting = true;
    		}
    		else {
    			lifting = false;
    		}
    	}
    		
    	
    	if (LiftDown) {
    		if (!Robot.Lift.isDown()) {
    			Robot.Lift.liftDown();
    			lowering = true;
    		}
    		else {
    			lowering = false;
    		}
    	}
    		
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
		
		
    	SmartDashboard.putNumber(" old Gyro", RobotMap.Gyro.getAngle());
    	
    	SmartDashboard.putBoolean("Grabbing", grabbing);
    	SmartDashboard.putBoolean("Releasing", releasing);
    	SmartDashboard.putNumber("pot", Robot.intake.getPot());
    	SmartDashboard.putBoolean("Limit get valaue", RobotMap.liftLimitSwitch.get());
    	SmartDashboard.putNumber("SetPoint", Robot.intake.flipPID.getSetpoint());
    	
    	SmartDashboard.putBoolean("PID is on", Robot.intake.PIDactive());
    	SmartDashboard.putBoolean("Flip Button Active", flipActive);
    	SmartDashboard.putNumber("LiftPosition", CurrentLift);
    	
    	SmartDashboard.putNumber("POV", Robot.oi.operator.getPOV());
    	SmartDashboard.putBoolean("D_UP", D_Up);
    	SmartDashboard.putBoolean("D_down", D_Down);
    	SmartDashboard.putBoolean("intaking", intaking);
    	SmartDashboard.putBoolean("outTaking", outtaking);

    	SmartDashboard.putNumber("Drive Power", power);
    	SmartDashboard.putNumber("Drive direction", direction);
    	
    	SmartDashboard.putNumber("Left Intake", RobotMap.intakeLeft.get());
    	SmartDashboard.putNumber("Right Intake", RobotMap.intakeRight.get());
    	SmartDashboard.putNumber("flipperPower", RobotMap.flipper.get());
    	SmartDashboard.putNumber("Left 1", RobotMap.driveTrainLeft1.get());
    	SmartDashboard.putNumber("Left 2", RobotMap.driveTrainLeft2.get());
    	SmartDashboard.putNumber("Right 1", RobotMap.driveTrainRight1.get());
    	SmartDashboard.putNumber("Right 2", RobotMap.driveTrainRight2.get());
    	
    	SmartDashboard.putNumber("Drive Ticks", RobotMap.driveEncoder.get());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
