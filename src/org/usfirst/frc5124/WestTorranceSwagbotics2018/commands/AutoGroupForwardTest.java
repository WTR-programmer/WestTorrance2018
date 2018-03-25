package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class AutoGroupForwardTest extends CommandGroup {

	public AutoGroupForwardTest(){
		//addSequential(new AutoDriveForward(.5, 144));
		
		//addSequential(new AutoTurnWithGyro(25));
//		addSequential(new AutoDriveWIthPID(6 * Math.PI));
		//addSequential(new AutoDriveForward(.7,2));
		addSequential(new AutoTurnWithPID(180));

	}
}
