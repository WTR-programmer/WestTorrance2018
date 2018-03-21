package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class GrabUngrab extends Command {

	char pos;
	private boolean isFinished;
	public final static char GRAB = 0x11;
	public final static char UNGRAB = 0x18;
	
	public GrabUngrab(char pos) {
		requires(Robot.intake);
		this.pos = pos;
		isFinished = false;
	}
	
	public void execute() {
//		if(pos == UNGRAB) {
//			RobotMap.clamp1.set(Value.kReverse);
//			RobotMap.clamp2.set(Value.kReverse);
//		}
//		if(pos == GRAB) {
//			RobotMap.clamp1.set(Value.kForward);
//			RobotMap.clamp2.set(Value.kForward);
//		}
		Robot.intake.Ungrab();
		isFinished = true;
	}

	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	
	public void end() {
	}

}
