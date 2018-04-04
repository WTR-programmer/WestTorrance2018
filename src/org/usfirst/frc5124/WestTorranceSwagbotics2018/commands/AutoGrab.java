package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class AutoGrab extends Command {

	char pos;
	private boolean isFinished;
	public final static char GRAB = 0x11;
	public final static char UNGRAB = 0x18;
	
	public AutoGrab() {
		this.pos = pos;
		isFinished = false;
		setTimeout(1);
	}
	
	public void execute() {
//		
		Robot.intake.Grab();
		if (isTimedOut()) {
		isFinished = true;
		}
		else {
			isFinished = false;
		}
		
	}

	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	
	public void end() {
	}

}
