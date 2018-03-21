package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FlipPower extends Command {

	double pos;
	boolean ArmDone;
	double speed;
	double time;
	
	public FlipPower(double power, double seconds) {
		speed = power;
		time = seconds;
		setTimeout(time);
	}
	public void initDefaultCommand() {
		ArmDone = false;
    }
	
	public void execute() {
		Robot.intake.flipPID.disable();
		
		if (isTimedOut()) {
			ArmDone = true;
			Robot.intake.setFlipperPower(0);
		}
		else {
			Robot.intake.setFlipperPower(-.2);
			ArmDone = false;
		}
			
		
		
		SmartDashboard.putNumber("Pot", RobotMap.pot.get());
		SmartDashboard.putNumber("Arm setPoint", Robot.intake.flipPID.getSetpoint());
		SmartDashboard.putNumber("flipperPower", RobotMap.flipper.get());
		SmartDashboard.putBoolean("ArmDone", ArmDone);
		SmartDashboard.putNumber("ArmTime", time);

	}

	@Override
	protected boolean isFinished() {
		return ArmDone;
		}
	
	public void end() {
	}

}
