package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.Wait;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoTurnWithGyro extends Command {

	
	private double DegreesToTurn;
	private double DegreesTurned;
	private double DegreesInit;
	private double time;
	private boolean Right;
	private boolean done;
	private double turn;
	
	
    public AutoTurnWithGyro(double Degrees) {
    	requires(Robot.driveTrain);
    	DegreesToTurn = Degrees;
    	if (Degrees > 0) {
    		Right = true;
    	}
    	else {
    		Right = false;
    	}
    	time = System.currentTimeMillis();
    	DegreesInit = RobotMap.Gyro.getAngle();
    }

    @Override
    protected void initialize() {
    	done = false;
    	RobotMap.driveTrainRight1.setInverted(false);
    	RobotMap.driveTrainRight2.setInverted(false);
    	turn = .6;
    }

   
    @Override
    protected void execute() {
    	//DegreesTurned += RobotMap.Gyro.getRate() / (System.currentTimeMillis() - time);
    	DegreesTurned = RobotMap.Gyro.getAngle() - DegreesInit;
    	time = System.currentTimeMillis();
    	if (Math.abs(DegreesTurned) < Math.abs(DegreesToTurn)) {
    		if (Right) {
    		Robot.driveTrain.tankDrive(turn, -turn);
    		}
    		else {
    			Robot.driveTrain.tankDrive(-turn, turn);
    		}
    		done = false;
    	}
    	else {
    		//Robot.driveTrain.tankDrive(-turn,turn);
    		done = true;
    	}
    	SmartDashboard.putNumber("Get Angle", RobotMap.Gyro.getAngle());
    	SmartDashboard.putNumber("GetRate", RobotMap.Gyro.getRate());
    	SmartDashboard.putNumber("Gyro PID return", RobotMap.Gyro.pidGet());
    	SmartDashboard.putNumber("Degree Target", DegreesToTurn);
    	SmartDashboard.putBoolean("Gyro Done", done);
    //	SmartDashboard.putNumber("Calibration", RobotMap.Gyro.calibrate());

    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void end() {
    	Robot.driveTrain.tankDrive(0, 0);
    }

    @Override
    protected void interrupted() {
    }
}
