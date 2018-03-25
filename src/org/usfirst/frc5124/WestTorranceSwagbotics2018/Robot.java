package org.usfirst.frc5124.WestTorranceSwagbotics2018;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import org.usfirst.frc5124.WestTorranceSwagbotics2018.subsystems.*;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.commands.*;

public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    public static DriveTrain driveTrain;
    public static Lift Lift;
    public static Intake intake;
    public static String gameData;
    public static EncoderLoop EncoderLoop;
    public static GyroLoop GyroLoop;
    public static LiftAscend LiftAscend;
    public static LiftDescend LiftDescend;    
   // public static Flipper Flipper;

    @Override
    public void robotInit() {
    	
        RobotMap.init();
        
        driveTrain = new DriveTrain();
        
        oi = new OI();
        intake = new Intake();
        Lift = new Lift();
        
        CameraServer.getInstance().startAutomaticCapture(0);
        
        //chooser.addDefault("Autonomous Command", new );

    }

    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
        //autonomousCommand = new RegionalAutoLeft();
    	
    	//autonomousCommand = new AutoGroupSwitchRight();
        
    	//autonomousCommand = new AutoGroupGameData();

    	autonomousCommand = new AutoGroupForwardTest();

//    	autonomousCommand = new Wait(4);
    	
    	//autonomousCommand = new AutoGroupFlipPosTest();
    	
    	//autonomousCommand = new RegionalAutoBothButRight();
    	
        if (autonomousCommand != null) autonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}