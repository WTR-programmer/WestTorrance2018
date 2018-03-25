package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupGameData extends CommandGroup {
	
	char pos = 'L';
	//double inToSec = 1 / (18 * 12);
	double power = 1;
	
	public AutoGroupGameData(){
		String GameData = Robot.gameData;
		if(pos == 'C') {
			addSequential(new AutoDriveWIthPID(3 * 12));
			addSequential(new AutoTurnWithGyro(90));
			addSequential(new AutoDriveWIthPID(6 * 12));
			addSequential(new AutoTurnWithGyro(-90));
			addSequential(new AutoDriveWIthPID(-3.5 * 12));			
			pos = 'R';
		}
		//go to scale
			addSequential(new AutoDriveWIthPID(230));
			if(GameData.charAt(1) != pos) {
				switchSides(16 * 12);
				if(pos == 'L') {
					pos = 'R';
				}
				else {
					pos = 'L';
				}
			}
			addSequential(new AutoDriveWIthPID(70));
			if(pos == 'R') {
				addSequential(new AutoTurnWithGyro(-45));
			}
			else {
				addSequential(new AutoTurnWithGyro(45));
			}
		//out take
			addSequential(new FlipPos(0.3));
			addSequential(new LiftAscend(LiftAscend.SCALE_POS));
			addSequential(new Wait(1));
			addSequential(new IntakePower(1));
			addSequential(new Wait(.5));
			addSequential(new IntakePower(0));
			addSequential(new LiftAscend(LiftAscend.BOTTOM));
		//get another block
			if(pos == 'R') {
				addSequential(new AutoTurnWithGyro(-135));
				addSequential(new AutoDriveWIthPID(70));
				addSequential(new AutoTurnWithGyro(90));
				addSequential(new AutoDriveWIthPID(25.25));
				addSequential(new AutoTurnWithGyro(90));
			}
			else {
				addSequential(new AutoTurnWithGyro(135));
				addSequential(new AutoDriveWIthPID(70));
				addSequential(new AutoTurnWithGyro(-90));
				addSequential(new AutoDriveWIthPID(25.25));
				addSequential(new AutoTurnWithGyro(-90));
			}
			if (pos != GameData.charAt(0)) {
				switchSides(16 * 12 - 25.25);
				if(pos == 'L') {
					pos = 'R';
				}
				else {
					pos = 'L';
				}
			}
			addSequential(new AutoTurnWithGyro(180));
			addSequential(new FlipPos(0.1));
			addSequential(new GrabUngrab(GrabUngrab.UNGRAB));
			addSequential(new AutoDriveWIthPID(37));
			addSequential(new IntakePower(-1));
			addSequential(new GrabUngrab(GrabUngrab.GRAB));
			addSequential(new Wait(0.5));
		//put in switch
			addSequential(new IntakePower(0));
			addSequential(new AutoDriveWIthPID(-10));
			addSequential(new LiftAscend(LiftAscend.SWITCH_POS));
			addSequential(new AutoDriveWIthPID(22));
			addSequential(new IntakePower(1));
			addSequential(new Wait(0.5));
			addSequential(new IntakePower(0));
		//do an extra massive yeet
			addSequential(new LiftAscend(LiftAscend.BOTTOM));
			addSequential(new AutoDriveWIthPID(-50));
			addSequential(new AutoTurnWithGyro(180));
			if (pos != GameData.charAt(1)) {
				switchSides(16 * 12 - 25.25 - 13.2);
				if(pos == 'L') {
					pos = 'R';
				}
				else {
					pos = 'L';
				}
			}
			else {
				switchSides(13.2);
			}
			addSequential(new AutoTurnWithGyro(180));
			addSequential(new FlipPos(0.1));
			addSequential(new GrabUngrab(GrabUngrab.UNGRAB));
			addSequential(new AutoDriveWIthPID(37));
			addSequential(new IntakePower(-1));
			addSequential(new GrabUngrab(GrabUngrab.GRAB));
			addSequential(new Wait(0.5));
			addSequential(new AutoDriveWIthPID(-20));
			addSequential(new AutoTurnWithGyro(180));
			switchSides(-15);
			addSequential(new LiftAscend(LiftAscend.SCALE_POS));
			addSequential(new AutoDriveWIthPID(75));
			addSequential(new FlipPos(0.3));
			addSequential(new Wait(1));
			addSequential(new IntakePower(1));
			addSequential(new Wait(.5));
			addSequential(new IntakePower(0));
			addSequential(new AutoDriveWIthPID(-20));
			addSequential(new LiftAscend(LiftAscend.BOTTOM));
	}

	private void switchSides(double inOut) {
		int l = pos == 'L' ? -1 : 1;
		addSequential(new AutoTurnWithGyro(l * 90));
		addSequential(new AutoDriveWIthPID(inOut));
		addSequential(new AutoTurnWithGyro(l * -90));
			if(pos == 'L') {
				pos = 'R';
			}
			else {
				pos = 'L';
			}
		
	}
	
}
