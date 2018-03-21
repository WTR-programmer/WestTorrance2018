package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class DisableFlip extends Command {

	double pos;
	boolean ArmDone;
	
	public DisableFlip() {
	}
	public void initDefaultCommand() {
    }
	
	public void execute() {
		Robot.intake.flipPID.disable();
		ArmDone = true;
	}

	@Override
	protected boolean isFinished() {
		return ArmDone;
		}
	
	public void end() {
	}

}
