package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoDriveInchesWithP extends Command {

	public double inches;
	public boolean done;
	public int initialEncoderPos;
	public int target;
	double wheelDiameter = 6;
	double wheelCircumference = Math.PI * wheelDiameter;
	double ticksPerRevolution = 1024;
	double ticksPerInch = ticksPerRevolution/wheelCircumference;
	
    public AutoDriveInchesWithP(double i) {
    	inches = i;
    	done = false;
    	initialEncoderPos = RobotMap.driveEncoder.get();
    	target = initialEncoderPos + (int) (inches * ticksPerInch);
    	RobotMap.driveTrainRight1.setInverted(true);
    	RobotMap.driveTrainRight2.setInverted(true);
    	RobotMap.driveTrainLeft1.setInverted(false);
    	RobotMap.driveTrainLeft2.setInverted(false);
    }

    @Override
    protected void initialize() {
    }

   
    @Override
    protected void execute() {
    	int pos = RobotMap.driveEncoder.get();
    	if (Math.abs(target - pos) < 10 && RobotMap.driveEncoder.getRate() < 50) {
    		done = true;
    		RobotMap.EntireDrive.set(0);
    		return;
    	}
    	RobotMap.EntireDrive.set((target - pos) / 1000.0);
    	SmartDashboard.putNumber("Speed", (target - pos) / 1000.0);
    	SmartDashboard.putNumber("Pos", RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Target", target);
    	SmartDashboard.putBoolean("done", done);
    	//SmartDashboard.putBoolean("Wait Time Out", isTimedOut());
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
