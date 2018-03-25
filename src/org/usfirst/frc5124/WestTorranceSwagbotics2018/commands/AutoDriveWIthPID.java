package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.RobotDrive;

public class AutoDriveWIthPID extends Command {

	private double ticks;
	private double power;
	private double left;
//	private double pulsesmoved;
//	private double pulsestomove;
	private double ticksToMove;
	private double ticksMoved;
	//private final DifferentialDrive differentialDrive = RobotMap.driveTrainDifferentialDrive;
	double wheelDiameter = 6;
	double wheelCircumference = Math.PI * wheelDiameter;
//	double pulsesPerRevolution = 4096;
	double ticksPerRevolution = 1024;
//	double pulsesPerInch = pulsesPerRevolution/wheelCircumference;
	double ticksPerInch = ticksPerRevolution/wheelCircumference;
	boolean done;
	
	
	private PIDController drivePID = Robot.driveTrain.DrivePID;
	private PIDController turnPID = Robot.driveTrain.TurnPID;
	
//	private PIDController powerPID = Robot.driveTrain.PowerPID;
//	private PIDController adjustPID = Robot.driveTrain.AdjustPID;
//	
//	double TurnValue = Robot.driveTrain.AdjustPID.get();
//	double PowerValue = Robot.driveTrain.PowerPID.get();
	
    public AutoDriveWIthPID( double inches) {
    	requires(Robot.driveTrain);
  //  	pulsestomove = inches * pulsesPerInch;
    	ticksToMove = inches * ticksPerInch;
    }

    @Override
    protected void initialize() {
    	done = false;
    	//timeout Just in case (This might turn the command into a time function, if the PID doesn't work)
    	//setTimeout(10);
    	RobotMap.driveEncoder.reset();
    	RobotMap.Gyro.reset();
    	RobotMap.driveTrainRight1.setInverted(false);
    	RobotMap.driveTrainRight2.setInverted(false);
    	Robot.driveTrain.PowerPID.setOutputRange(-.7, .7);
    	//Robot.driveTrain.DrivePID.setAbsoluteTolerance(100);
    	Robot.driveTrain.PowerPID.setAbsoluteTolerance(10);

    	//Set time out
    	setTimeout(2.5);
    }

   
    @Override
    protected void execute() {
    	Robot.driveTrain.AdjustPID.enable();
    	Robot.driveTrain.PowerPID.enable();
    	Robot.driveTrain.AdjustPID.setSetpoint(0);
    	Robot.driveTrain.PowerPID.setSetpoint(ticksToMove);
    	Robot.driveTrain.arcadeDrive(Robot.driveTrain.PowerPID.get(), Robot.driveTrain.AdjustPID.get());
    	//Robot.driveTrain.arcadeDrive(.5, 0);
    	if (Robot.driveTrain.PowerPID.onTarget()) {
    		done = true;
    		Robot.driveTrain.AdjustPID.disable();
        	Robot.driveTrain.PowerPID.disable();
        	Robot.driveTrain.arcadeDrive(0,0);
        	
    				}
    	else {
    		done = false;
    	}
    	//Shut off if it takes too long
    	if (isTimedOut()) {
    		done = true;
    	}    	
//    	drivePID.disable();
//    	turnPID.disable();
//    	Robot.EncoderLoop.enable();
//    	Robot.GyroLoop.enable();
//    	Robot.EncoderLoop.get
    	//disable direct PIDs
    	
//    	drivePID.enable();
//    	turnPID.disable();
//    	drivePID.setSetpoint(ticksToMove);
    	//Enable indirect PIDs
//    	adjustPID.enable();
//    	powerPID.enable();
//    	
//    	//Set Adjust to Turn to Zero
//    	adjustPID.setSetpoint(0);
//    	//Set Power to reach destination
//    	powerPID.setSetpoint(ticksToMove); 
//    	
//    	//set PID to Output from indirect PIDs set to doubles
//    	Robot.driveTrain.differentialDrive.arcadeDrive(PowerValue, TurnValue);
    	
    	
    	//Tru
    	
    	
    	//Get values to Report
//    	pulsesmoved = RobotMap.driveEncoder.getRaw();
    	ticksMoved = RobotMap.driveEncoder.get();
    	
    	//Report Values
//    	SmartDashboard.putNumber(" Drive PID PulsesToMove", pulsestomove);
//    	SmartDashboard.putNumber("Drive PID PulsesMoved", pulsesmoved);
    	SmartDashboard.putNumber("Drive PID ticks To Move", ticksToMove);
    	SmartDashboard.putNumber("Drive PID ticks Moved", ticksMoved);
    	
    	SmartDashboard.putNumber("Drive left1 power", RobotMap.driveTrainLeft1.get());
    	SmartDashboard.putNumber("Drive left2 power", RobotMap.driveTrainLeft2.get());
    	SmartDashboard.putNumber("Drive right1 power", RobotMap.driveTrainRight1.get());
    	SmartDashboard.putNumber("Drive right2 power", RobotMap.driveTrainRight2.get());
    	
    	SmartDashboard.putBoolean("Done", done);
    	
    	SmartDashboard.putNumber("adjustPid value", Robot.driveTrain.AdjustPID.get() );
    	SmartDashboard.putNumber("PowerPid value", Robot.driveTrain.PowerPID.get() );
    	SmartDashboard.putNumber(" old Gyro", RobotMap.Gyro.getAngle());
    	SmartDashboard.putNumber("new gyro", RobotMap.newGyro.getAngle());
    	
    	SmartDashboard.putNumber("left Intake", RobotMap.intakeLeft.get());
    	SmartDashboard.putNumber("right Intake",RobotMap.intakeRight.get());
    	
    	SmartDashboard.putBoolean("power on target", Robot.driveTrain.PowerPID.onTarget());
    	
    	
    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void end() {
    	//RobotMap.driveTrainRight1.setInverted(false);
    	//RobotMap.driveTrainRight2.setInverted(false);
//    	RobotMap.EntireDrive.set(0);
    	Robot.driveTrain.DrivePID.disable();
    	Robot.driveTrain.TurnPID.disable();

    }

    @Override
    protected void interrupted() {
    	//RobotMap.driveTrainRight1.setInverted(false);
    	//RobotMap.driveTrainRight2.setInverted(false);
    }
}
