package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupGameData extends CommandGroup {
	
	char pos = 'L';
	double inToSec = 1 / (18 * 12);
	double power = 1;
	
	public AutoGroupGameData(){
		String GameData = Robot.gameData;
		if(pos == 'C') {
			addSequential(new AutoDriveWithInches(3 * 12));
			addSequential(new AutoTurnWithGyro(90));
			addSequential(new AutoDriveWithInches(6 * 12));
			addSequential(new AutoTurnWithGyro(-90));
			addSequential(new AutoDriveWithInches(-3.5 * 12));			
			pos = 'R';
		}
		//go to scale
			addSequential(new AutoDriveWithInches(230));
			if(GameData.charAt(1) != pos) {
				switchSides(pos);
				if(pos == 'L') {
					pos = 'R';
				}
				else {
					pos = 'L';
				}
			}
			addSequential(new AutoDriveWithInches(70));
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
				addSequential(new AutoDriveWithInches(70));
				addSequential(new AutoTurnWithGyro(90));
				addSequential(new AutoDriveWithInches(25.25));
				addSequential(new AutoTurnWithGyro(-90));
			}
			else {
				addSequential(new AutoTurnWithGyro(135));
				addSequential(new AutoDriveWithInches(70));
				addSequential(new AutoTurnWithGyro(-90));
				addSequential(new AutoDriveWithInches(25.25));
				addSequential(new AutoTurnWithGyro(90));
			}
			addSequential(new FlipPos(0.1));
			addSequential(new GrabUngrab(GrabUngrab.UNGRAB));
			addSequential(new AutoDriveWithInches(37));
			addSequential(new IntakePower(-1));
			addSequential(new GrabUngrab(GrabUngrab.GRAB));
			addSequential(new Wait(500));
		//put in switch
			addSequential(new IntakePower(0));
			addSequential(new LiftAscend(1));
			addSequential(new IntakePower(1));
			addSequential(new Wait(500));
			addSequential(new IntakePower(0));
		
	}

	private void switchSides(char lastSide) {
		int l = pos == 'L' ? -1 : 1;
		addSequential(new AutoTurnWithGyro(l * 90));
		addSequential(new AutoDriveWithInches(16 * 12));
		addSequential(new AutoTurnWithGyro(l * -90));
	}
	
	private class AutoDriveWithInches extends AutoDriveForward {

		public AutoDriveWithInches(double speed, double inches) {
			super(speed, inches * inToSec);
		}
		
		public AutoDriveWithInches(double inches) {
			super(power, inches * inToSec);
		}
		
		
	}
	
}
