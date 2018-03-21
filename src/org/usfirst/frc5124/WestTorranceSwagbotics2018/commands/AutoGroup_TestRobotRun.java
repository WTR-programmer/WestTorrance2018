package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroup_TestRobotRun extends CommandGroup {
	//Btw, The robot is actually off center by 4 inches
	char Left = 'L';
	char Right = 'R';
	String GameData;
	char Switch;
	char Balance;
	double Turn1;
	double Turn2;
	double Turn3;
	
	public AutoGroup_TestRobotRun(){
		
		GameData = Robot.gameData;
		Switch = GameData.charAt(0);
		Balance = GameData.charAt(1);
		//Figure our direction
		if (Switch == Left) {
			Turn1 = -1;
			Turn2 = 1;
			Turn3 = 1;
		}
		else {
			Turn1 = 1;
			Turn2 = -1;
			Turn3 = -1;
		}
		//Ordinary Saturday
		addSequential(new AutoDriveWIthPID(49));
		//Stuff Gets Crazy
		addSequential(new Wait(1));
		addSequential(new AutoTurnWithPID(90 * Turn1));
		addSequential(new Wait(1));
		addSequential(new AutoDriveWIthPID(71));
		addSequential(new Wait(1));
		addSequential(new AutoTurnWithPID(90 * Turn2));
		addSequential(new Wait(1));
		addSequential(new AutoDriveWIthPID(95));
		addSequential(new Wait(1));
		addSequential(new AutoTurnWithPID(90 * Turn3));

	}
}
