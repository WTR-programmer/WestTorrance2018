package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroup_NoPID extends CommandGroup {
	//Btw, The robot is actually off center by 4 inches
	char Left = 'L';
	char Right = 'R';
	String GameData;
	char Switch;
	char Balance;
	double Turn1;
	double Turn2;
	double Turn3;
	
	public AutoGroup_NoPID(){
		addSequential(new AutoDriveWIthPID(100));
		addSequential(new Wait(3));
		addSequential(new AutoTurnWithPID(180));
		addSequential(new Wait(3));
		addSequential(new AutoDriveWIthPID(100));
		addSequential(new Wait(3));
		addSequential(new AutoTurnWithPID(-180));
		addSequential(new Wait(3));
		addSequential(new AutoDriveWIthPID(100));
		addSequential(new Wait(3));
		addSequential(new AutoDriveWIthPID(-100));
	}
}
