package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class RegionalAutoLeft extends CommandGroup {
	
	char pos1;
	char pos2;
	
	public RegionalAutoLeft(){
		String GameData = Robot.gameData;
		pos1 = GameData.charAt(0);
		pos2 = GameData.charAt(1);
		
		//addSequential(new FlipPos(1));
		addSequential(new Wait(1));
		addSequential(new AutoDriveWIthPID(100));
//		if (pos1 == 'L') {
//			addSequential(new AutoOutTake(2));
//		}
		
		
	}

	private void switchSides(char lastSide) {
		// TODO Auto-generated method stub
		
	}
	
}
