package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupCenterSwitch extends CommandGroup {

	public AutoGroupCenterSwitch(){
		//addSequential(new AutoDriveForward(.5, 144));
		double Lowest_Flip = .61;
		double Highest_Flip = 1.3;
		double Middle_Flip = .8;
		String direction = Robot.gameData;
		char FirstPos = direction.charAt(0);
		char SecondPos = direction.charAt(1);
		char Left = 'L';
		char Right = 'R';
		
		
		if (FirstPos == 'L') {
		addSequential(new CommandCurveWithPID(75, -35, true, .7));
		addSequential(new CommandCurveWithPID(40, 40, true, .7));
		addParallel(new CommandWrist(Lowest_Flip));
		addSequential(new Wait(1));
		addSequential(new AutoGrab());
		}
		else {
			addSequential(new CommandCurveWithPID(60, 35, true, .7));
			addSequential(new CommandCurveWithPID(35, -25, true, .7));
			addSequential(new Wait(1));
			addSequential(new AutoGrab());
		}

	}
}
