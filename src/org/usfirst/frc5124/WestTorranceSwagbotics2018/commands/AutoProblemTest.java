package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoProblemTest extends Command {

	public double seconds;
	double ticksToMove;
	double power;
	boolean done;
	double diameter = 6;
	double ticksPerRev = 1024;
	double inchesPerRev = Math.PI * diameter;
	double ticksPerInch = ticksPerRev / inchesPerRev;
	
    public AutoProblemTest(double distance, double speed) {
    	ticksToMove = distance * ticksPerInch;
    	power = speed;
    }

    @Override
    protected void initialize() {
    	RobotMap.driveEncoder.reset();
    	RobotMap.newGyro.reset();
    	done = false;
    	RobotMap.driveTrainLeft1.setInverted(false);
    	RobotMap.driveTrainLeft2.setInverted(false);
    	RobotMap.driveTrainRight1.setInverted(false);
    	RobotMap.driveTrainRight2.setInverted(false);
    }

   
    @Override
    protected void execute() {
    	
    	Robot.driveTrain.PowerPID.enable();
    	Robot.driveTrain.AdjustPID.enable();
    	Robot.driveTrain.PowerPID.setAbsoluteTolerance(20);
    	Robot.driveTrain.PowerPID.setSetpoint(ticksToMove);
    	Robot.driveTrain.AdjustPID.setSetpoint(0);
    	
    	if (Robot.driveTrain.PowerPID.onTarget()) {
    		done = true;
    		Robot.driveTrain.PowerPID.disable();
        	Robot.driveTrain.AdjustPID.disable();
    	}
    	else if (Math.abs(RobotMap.driveEncoder.get()) > Math.abs(ticksToMove + 200)) {
    		done = true;
    		Robot.driveTrain.PowerPID.disable();
        	Robot.driveTrain.AdjustPID.disable();
    	}
    	else {
    		Robot.driveTrain.arcadeDrive(Robot.driveTrain.PowerPID.get(), Robot.driveTrain.AdjustPID.get());
    		done = false;
    	}
    	
    	
//    	if (Math.abs(RobotMap.driveEncoder.get()) < Math.abs(ticksToMove)) {
//    		Robot.driveTrain.tankDrive(power, power);
//    		done = false;
//    	}
//    	else {
//    		done = true;
//    	}
    	SmartDashboard.putNumber("Drive Ticks", RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Drive Ticks Raw", RobotMap.driveEncoder.getRaw());
    	SmartDashboard.putNumber("New Gyro", RobotMap.newGyro.getAngle());
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
