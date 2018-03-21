package org.usfirst.frc5124.WestTorranceSwagbotics2018.subsystems;

import java.awt.Robot;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.*;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.AutoTurnWithPID;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	public PIDOutput TurnOutput;
	public PIDOutput PowerOutput;
	 
   public final DifferentialDrive differentialDrive = RobotMap.driveTrainDifferentialDrive;
    private final Compressor compressor = RobotMap.driveTrainCompressor;
   public final PIDController DrivePID = new PIDController(1, 0 ,0, RobotMap.driveEncoder, RobotMap.EntireDrive);
   public final PIDController TurnPID = new PIDController(3,1,0, RobotMap.Gyro, RobotMap.EntireDrive);
   
   public final PIDController PowerPID = new PIDController(.0007,.1,0, RobotMap.driveEncoder, RobotMap.fakeMotor );
   public final PIDController AdjustPID = new PIDController(.1,0,0, RobotMap.Gyro, RobotMap.fakeMotor);
    
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveTrainJoystickPuppetry());
    }
    
    
    public void periodic() {
    }
    
    public void compressorOn() {													
    	compressor.setClosedLoopControl(true);
    	compressor.start();
    }
    
    public void compressorOff() {													
    	compressor.setClosedLoopControl(false);
    	compressor.stop();
    }
    
    public void tankDrive(double left , double right) {
    	differentialDrive.tankDrive(left , right);
    }
    public void arcadeDrive(double power, double direction) {
    	differentialDrive.arcadeDrive(power, direction);
    }
    public void Wait (double wait) {
    	
    }
    public PIDOutput GetTurnOutput() {
    	return TurnOutput;
    }
    /*
    public void runPID() {
    	RobotMap.EntireDrive.pidWrite(DrivePID.get());
    }
    */
    public void curvatureDrive(boolean distance) {
    	//differentialDrive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }
}

