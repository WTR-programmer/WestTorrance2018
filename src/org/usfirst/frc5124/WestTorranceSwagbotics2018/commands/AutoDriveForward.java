package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoDriveForward extends Command {

	private double time;
	private double ticks;
	private double power;
	private double left;
	private double pulsesmoved;
	private double pulsestomove;
	private final DifferentialDrive differentialDrive = RobotMap.driveTrainDifferentialDrive;
	double wheelDiameter = 6;
	double wheelCircumference = Math.PI * wheelDiameter;
	double pulsesPerRevolution = 1440;
	double pulsesPerInch = pulsesPerRevolution/wheelCircumference;
	boolean done;
	
    public AutoDriveForward(double speed, double seconds) {
    	requires(Robot.driveTrain);
    	power = speed;
    	//pulsestomove = inches * pulsesPerInch;
    	time = seconds;
    	//Robot.driveTrain.DrivePID.seta
    }

    @Override
    protected void initialize() {
    	done = false;
    	RobotMap.driveEncoder.reset();
    	RobotMap.Gyro.reset();
    	
    }

   
    @Override
    protected void execute() {
    	setTimeout(time);
    	pulsesmoved = RobotMap.driveEncoder.getRaw();
    	
    	Robot.driveTrain.AdjustPID.setSetpoint(0);
    	Robot.driveTrain.AdjustPID.enable();
    	if (isTimedOut()) {
    		done = true;
    	}
    	else {
    	Robot.driveTrain.arcadeDrive(power, Robot.driveTrain.AdjustPID.get());
    	done = false;
    	}
    	
    	
    	
    	
    	
//    	if (Math.abs(pulsesmoved) < Math.abs(pulsestomove)) {
//    	Robot.driveTrain.tankDrive (power, power);
//    		//Robot.driveTrain.arcadeDrive(Robot.driveTrain.PowerPID.get(), Robot.driveTrain.AdjustPID.get());
//    	done = false;
//    	}
//    	else {
//    	Robot.driveTrain.tankDrive(0, 0);
//    	done = true;
//    	}
//    	
    	
    	SmartDashboard.putNumber("PulsesToMove", pulsestomove);
    	SmartDashboard.putNumber("PulsesMoved", pulsesmoved);
    	
    	
    	SmartDashboard.putNumber("Drive left1 power", RobotMap.driveTrainLeft1.get());
    	SmartDashboard.putNumber("Drive left2 power", RobotMap.driveTrainLeft1.get());
    	SmartDashboard.putNumber("Drive right1 power", RobotMap.driveTrainLeft1.get());
    	SmartDashboard.putNumber("Drive right2 power", RobotMap.driveTrainLeft1.get());
    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
