package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoOutTake extends Command {

	boolean intakeDone;
	
	public AutoOutTake(double time) {
		setTimeout(time);
	}
	public void initDefaultCommand() {
    }
	
	public void execute() {
		Robot.intake.intakePower(.4);
		SmartDashboard.putBoolean("shoot done", isTimedOut());
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
		}
	
	public void end() {
		Robot.intake.disable();
	}

}
