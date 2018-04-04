package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.Wait;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoTurnWithPID extends Command {

	public double TurnPower;
	private double DegreesToTurn;
	private double DegreesTurned;
	private boolean Right;
	private boolean done;
	private double direction;
	
	
	
    public AutoTurnWithPID(double Degrees) {
    	requires(Robot.driveTrain);
    	DegreesToTurn = Degrees;
    	if (Degrees > 0) {
    		Right = true;
    		direction = 1;
    	}
    	else {
    		Right = false;
    		direction = -1;
    	}
    }

    @Override
    protected void initialize() {
    	RobotMap.Gyro.reset();
    	RobotMap.newGyro.reset();
    	done = false;
    	//Robot.driveTrain.Wait(4);
    	RobotMap.driveTrainRight1.setInverted(false);
    	RobotMap.driveTrainRight2.setInverted(false);
    	}

   
    @Override
    protected void execute() {
    	DegreesTurned = RobotMap.newGyro.getAngle();
    	//RobotMap.EntireDrive.set(Robot.driveTrain.TurnOutput);
    		Robot.driveTrain.TurnPID.enable();
    		Robot.driveTrain.TurnPID.setAbsoluteTolerance(2);
//    		Robot.driveTrain.TurnPID.setOutputRange(-.6 , .6);
    		Robot.driveTrain.TurnPID.setSetpoint(DegreesToTurn);
    		
    		if (Math.abs(Robot.driveTrain.TurnPID.get()) > .2) {
    		Robot.driveTrain.tankDrive(Robot.driveTrain.TurnPID.get(), -Robot.driveTrain.TurnPID.get());
    		}
    		else {
    			Robot.driveTrain.tankDrive(.2 * direction, .2 * -direction);
    		}
    		
    		if (Robot.driveTrain.TurnPID.onTarget()) {
    			Robot.driveTrain.TurnPID.disable();
    			done = true;
    			/*
    			if (Right) {
    			Robot.driveTrain.tankDrive(-1, 1);
    			} 
    			else {
    				Robot.driveTrain.tankDrive(1,  -1);
    			}
    			*/
    		}
    		else {
    			done = false;
    		}
    		
    		
    	SmartDashboard.putNumber("New Gyro", RobotMap.newGyro.getAngle());
    	SmartDashboard.putNumber("Old Gyro", RobotMap.Gyro.getAngle());
    	SmartDashboard.putNumber("Get Angle Raw", RobotMap.newGyro.getRawGyroX());
    	SmartDashboard.putNumber("GetRate", RobotMap.newGyro.getRate());
    	SmartDashboard.putNumber("Gyro PID return", RobotMap.newGyro.pidGet());
    	SmartDashboard.putNumber("Degree Target", DegreesToTurn);
    	SmartDashboard.putBoolean("Gyro Done", done);
    	SmartDashboard.putNumber("yaw", RobotMap.newGyro.getYaw());
    //	SmartDashboard.putNumber("Calibration", RobotMap.Gyro.calibrate());
    	
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
    	//Robot.driveTrain.tankDrive(0, 0);
    }

    @Override
    protected void interrupted() {
    }
}
