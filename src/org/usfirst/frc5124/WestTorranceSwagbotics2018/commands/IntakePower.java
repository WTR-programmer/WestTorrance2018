package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class IntakePower extends Command {

	double power;
	
	public IntakePower(double power) {
		this.power = power;
	}
	
	public void execute() {
		RobotMap.intakeLeft.set(power);
		RobotMap.intakeRight.set(-power);
	}

	@Override
	protected boolean isFinished() {
		return RobotMap.intakeLeft.get() == power;
	}

}
