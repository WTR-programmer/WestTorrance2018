package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class RegionalAutoBothButRight extends CommandGroup {
	
	char pos1;
	char pos2;
	
	public RegionalAutoBothButRight(){
		String GameData = Robot.gameData;
		pos1 = GameData.charAt(0);
		pos2 = GameData.charAt(1);
		
		addSequential(new Wait(1));											//
		addSequential(new FlipPos(.8));
		if (pos1 == 'L') {
			//Get forward
		addSequential(new AutoDriveWIthPID(50));
		addSequential(new Wait(1));
		//Turn to go left
		addSequential(new AutoTurnWithPID(-90));
		addSequential(new Wait(1));
		addSequential(new AutoDriveWIthPID(200));
		addSequential(new Wait(1));
		//Turn and drive to switch
		addSequential(new AutoTurnWithPID(90));
		addSequential(new Wait(1));
		addSequential(new AutoDriveWIthPID(50));
		addSequential(new Wait(1));
		addSequential(new AutoTurnWithPID(90));
		}
		else {
			addSequential(new AutoDriveWIthPID(50));
			addSequential(new Wait(1));
			addSequential(new AutoTurnWithPID(-90));
			addSequential(new Wait(1));
		}
		addSequential(new AutoOutTake(2));
		
		
	}

	private void switchSides(char lastSide) {
		// TODO Auto-generated method stub
		
	}
	
}
