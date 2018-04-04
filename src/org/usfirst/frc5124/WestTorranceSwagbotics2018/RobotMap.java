package org.usfirst.frc5124.WestTorranceSwagbotics2018;

import edu.wpi.first.wpilibj.AnalogInput;
import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.AHRSProtocol;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.SPI;

public class RobotMap {
	//private static double b;
	//private static double a;
	//private static PIDController massivelyCancer = new PIDController(1, 10, 0, (PIDSource) (Object) new Double(a), (PIDOutput) (Object) new Double(b));
    
	//DRIVE TRAIN
	//Drive Train Left Side
	public static SpeedController driveTrainLeft1;
    public static SpeedController driveTrainLeft2;
    public static SpeedControllerGroup driveTrainSpeedControllerLeft;
    //Drive Train Right Side
    public static SpeedController driveTrainRight1;
    public static SpeedController driveTrainRight2;
    public static SpeedControllerGroup driveTrainSpeedControllerRight;
    //Drive Train Diff Drive
    public static DifferentialDrive driveTrainDifferentialDrive;
    public static Encoder driveEncoder;
    //public static SpeedControllerGroup EntireDrive;
    //Compressor 
    public static Compressor driveTrainCompressor;
    
    //Intake
    public static SpeedController intakeLeft;
    public static SpeedController intakeRight;
    public static SpeedController flipper;
    public static PIDController flipperPID;
    public static AnalogPotentiometer pot;
    
    //Lift
    public static SpeedController lift1;
    public static SpeedController lift2;
    public static SpeedControllerGroup liftSpeed;
    public static Encoder liftEncoder;
    public static DigitalInput liftLimitSwitch;
    public static DoubleSolenoid brake;
    
    //clamp
    public static DoubleSolenoid clamp1;
    public static DoubleSolenoid clamp2;
    
    //camera
    public static CameraServer MagicEye;
    
    //Gyro
    public static ADXRS450_Gyro Gyro;
    public static AHRS newGyro;
    
    //Fake Motor
    public static  SpeedController fakeMotor;
    
    
    public static void init() {
        //Drive Train Left Side
    	//a = 0;
    	//massivelyCancer.setSetpoint(1);
    	//SmartDashboard.putNumber("Cancer Mass", b);
    	
    	driveTrainLeft1 = new VictorSP(3);
        driveTrainLeft1.setInverted(false);
        driveTrainLeft2= new VictorSP(4);
        driveTrainLeft2.setInverted(false);
        driveTrainSpeedControllerLeft = new SpeedControllerGroup(driveTrainLeft1 , driveTrainLeft2 );
        
        //Drive Train Right Side
        driveTrainRight1 = new VictorSP(5);
        driveTrainRight1.setInverted(false);
        driveTrainRight2 = new VictorSP(6);
        driveTrainRight2.setInverted(false);
        driveTrainSpeedControllerRight = new SpeedControllerGroup(driveTrainRight1 , driveTrainRight2 );
        
        //Drive Train Diff Drive
        driveTrainDifferentialDrive = new DifferentialDrive(driveTrainSpeedControllerLeft, driveTrainSpeedControllerRight);
        driveTrainDifferentialDrive.setSafetyEnabled(true);
        driveTrainDifferentialDrive.setExpiration(0.1);
        driveTrainDifferentialDrive.setMaxOutput(1.0);
        driveEncoder = new Encoder(0,1);
        
        //EntireDrive = new SpeedControllerGroup(driveTrainLeft1 , driveTrainLeft2, driveTrainRight1 , driveTrainRight2);
//        PIDController PowerPID = new PIDController(.0005,0,0, RobotMap.driveEncoder, Robot.driveTrain.PowerOutput );
//        PIDController AdjustPID = new PIDController(1,0,0, RobotMap.Gyro, Robot.driveTrain.TurnOutput);
        
        //Compressor
        driveTrainCompressor = new Compressor(0);
        driveTrainCompressor.setClosedLoopControl(true);
        
        //Intake
    	intakeLeft = new VictorSP(0);
        intakeLeft.setInverted(false);
        intakeRight = new VictorSP(1);
        intakeRight.setInverted(true);
        flipper = new VictorSP(2);
        intakeRight.setInverted(false);
        pot = new AnalogPotentiometer(0);
       // flipperPID = new PIDController(0, 0, 0, 0, pot, flipper);

        //Lift
        lift1 = new VictorSP(7);
        lift1.setInverted(false);
        lift2 = new VictorSP(8);
        lift1.setInverted(false);

        liftSpeed = new SpeedControllerGroup(lift1 , lift2);
        liftEncoder = new Encoder(2 , 3);
        liftLimitSwitch = new DigitalInput(4);
        brake = new DoubleSolenoid(0,1);
        
        //clamp
        clamp1 = new DoubleSolenoid(2,3);
        clamp2 = new DoubleSolenoid(4,5);
        
        //Camera
      //  MagicEye = new CameraServer();
        
        //Gyro
        Gyro = new ADXRS450_Gyro(); 
//        Gyro.calibrate();
//    	Gyro.reset();
        
    	newGyro = new AHRS(SPI.Port.kMXP);
    	
        //Fake Motor
        fakeMotor = new VictorSP(9);
        
    }
}