package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupSwitchRight extends CommandGroup {
	
	char pos1;
	char pos2;
	
	public AutoGroupSwitchRight(){
		String GameData = Robot.gameData;
		pos1 = GameData.charAt(0);
		pos2 = GameData.charAt(1);
		
		if (GameData.charAt(0) == 'R') {
		//addSequential(new FlipPos(1));
		addSequential(new Wait(1));
		addSequential(new LiftAscend(1));
		addSequential(new Wait(1));
		addSequential(new AutoDriveForward(.7, 3));
		addSequential(new Wait(1));
//		addSequential(new FlipPower(-.4, 1));
//		addSequential(new Wait(1));
//		addSequential(new AutoOutTake(2));
//		addSequential(new Wait(1));
		addSequential(new GrabUngrab(GrabUngrab.UNGRAB));
		addSequential(new Wait(1));
		addSequential(new AutoOutTake(3));
		addSequential(new Wait(1));
		addSequential(new AutoUnGrab());
		
		}
		else {
			addSequential(new AutoDriveForward(.7, 2));
			 
		}
		
		//addSequential(new );
		
		
	}
	
}
