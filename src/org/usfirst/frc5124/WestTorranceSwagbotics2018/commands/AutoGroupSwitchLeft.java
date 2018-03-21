package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupSwitchLeft extends CommandGroup {
	
	char pos1;
	char pos2;
	
	public AutoGroupSwitchLeft(){
		String GameData = Robot.gameData;
		pos1 = GameData.charAt(0);
		pos2 = GameData.charAt(1);
		
		if (GameData.charAt(0) == 'L') {
		addSequential(new FlipPos(1));
		addSequential(new Wait(1));
		addSequential(new LiftAscend(1));
		addSequential(new Wait(1));
		//addSequential(new AutoDriveWIthPID(.5));
		addSequential(new Wait(1));
		addSequential(new AutoOutTake(2));
		addSequential(new FlipPos(.61));
		//addSequential(new Wait(1));
		//addSequential(new GrabUngrab(GrabUngrab.GRAB));
		//addSequential(new GrabUngrab(GrabUngrab.GRAB));
		}
		else {
			addSequential(new AutoDriveWIthPID(100));
			 
		}
		
		//addSequential(new );
		
		
	}
	
}
