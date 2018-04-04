package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupLeftScale extends CommandGroup {

	public AutoGroupLeftScale(){
		//addSequential(new AutoDriveForward(.5, 144));
		double Lowest_Flip = .61;
		double Highest_Flip = 1.3;
		double Middle_Flip = .8;
		String direction = Robot.gameData;
		char FirstPos = direction.charAt(0);
		char SecondPos = direction.charAt(1);
		char Left = 'L';
		char Right = 'R';
		
		if (FirstPos == Right) {
			addSequential(new AutoDriveWIthPID(180));
			addSequential(new CommandCurveWithPID(160, 90, true, .6));
//			addSequential(new CommandCurveWithPID(70, -90, false, .2));
			addSequential(new AutoTurnWithPID(-90));
		}
		else {
			addSequential(new AutoDriveWIthPID(180));
			addSequential(new CommandCurveWithPID(64, 40, true, .4));
		}
		

	}
}
