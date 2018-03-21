package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FlipPos extends Command {

	double pos;
	boolean ArmDone;
	
	public FlipPos(double position) {
		pos = position;
	}
	public void initDefaultCommand() {
		//Robot.intake.flipPID.setAbsoluteTolerance(.05);
		ArmDone = false;
		setTimeout(2);
    }
	
	public void execute() {
		setTimeout(2);
		Robot.intake.flipPID.enable();
		Robot.intake.setFlipperSetpoint(pos);
		
		if (isTimedOut()) {
			ArmDone = true;
		}
		else {
			ArmDone = false;
		}
			
		
		
		SmartDashboard.putNumber("Pot", RobotMap.pot.get());
		SmartDashboard.putNumber("Arm setPoint", Robot.intake.flipPID.getSetpoint());
		SmartDashboard.putNumber("flipperPower", RobotMap.flipper.get());
		SmartDashboard.putBoolean("ArmDone", ArmDone);
	}

	@Override
	protected boolean isFinished() {
		return ArmDone;
		}
	
	public void end() {
	}

}
