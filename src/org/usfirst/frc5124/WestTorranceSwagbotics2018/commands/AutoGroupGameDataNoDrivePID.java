package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupGameDataNoDrivePID extends CommandGroup {
	
	char pos = 'R';
	double power = .8;
	public AutoGroupGameDataNoDrivePID(){
		String GameData = Robot.gameData;
		if(pos == 'C') {
			addSequential(new AutoDriveForward(power, 36));
			addSequential(new AutoTurnWithGyro(90));
			addSequential(new AutoDriveForward(power, 72));
			addSequential(new AutoTurnWithGyro(-90));
			addSequential(new AutoDriveForward(-power, 40));			
			pos = 'R';
		}
		//go to scale
			addSequential(new AutoDriveForward(power, .75));
			if(GameData.charAt(1) != pos) {
				switchSides(pos);
				if(pos == 'L') {
					pos = 'R';
				}
				else {
					pos = 'L';
				}
			}
			addSequential(new AutoDriveForward(power, .25));
			if(pos == 'R') {
				addSequential(new AutoTurnWithGyro(-45));
			}
			else {
				addSequential(new AutoTurnWithGyro(45));
			}
		//out take
			addSequential(new LiftAscend(1));
			addSequential(new IntakePower(1));
			addSequential(new FlipPos(0.3));
			addSequential(new Wait(.5));
			addSequential(new IntakePower(0));
			addSequential(new LiftAscend(-1));
		//get another block
			if(pos == 'R') {
				addSequential(new AutoTurnWithGyro(-135));
				//addSequential(new AutoDriveForward(70));
				addSequential(new AutoTurnWithGyro(90));
				//addSequential(new AutoDriveForward(25.25));
				addSequential(new AutoTurnWithGyro(-90));
			}
			else {
				addSequential(new AutoTurnWithGyro(135));
				//addSequential(new AutoDriveForward(70));
				addSequential(new AutoTurnWithGyro(-90));
				//addSequential(new AutoDriveForward(25.25));
				addSequential(new AutoTurnWithGyro(90));
			}
			addSequential(new FlipPos(0.1));
			addSequential(new GrabUngrab(GrabUngrab.UNGRAB));
			//addSequential(new AutoDriveForward(37));
			addSequential(new IntakePower(-1));
			addSequential(new GrabUngrab(GrabUngrab.GRAB));
			addSequential(new Wait(500));
		//put in switch
			addSequential(new IntakePower(0));
			//addSequential(new LiftAscend(/*250*/));
			addSequential(new IntakePower(1));
			addSequential(new Wait(500));
			addSequential(new IntakePower(0));
		
	}

	private void switchSides(char lastSide) {
		int l = pos == 'L' ? -1 : 1;
		addSequential(new AutoTurnWithGyro(l * 90));
		//addSequential(new AutoDriveForward(16 * 12));
		addSequential(new AutoTurnWithGyro(l * -90));
	}
	
}
