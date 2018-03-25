package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class LiftAscend extends Command {

	public static final double SWITCH_POS = 0.5;
	public static final double SCALE_POS = 1;
	public static final double BOTTOM = 0;
	private double ticksToMove;
	private double ticksMoved;
	private double power;
	private boolean done;
	private double currentPos = 0;
	double time;
	boolean liftDone;
	
    public LiftAscend(double Seconds) {
    	time = Seconds;
    	
    }

    @Override
    protected void initialize() {
    	done = false;
    }

    @Override
    protected void execute() {
    	setTimeout(time);
    	Robot.Lift.liftUp();
    	if (isTimedOut()) {
    		liftDone = true;
    	}
    	else {
    		liftDone = false;
    	}
    }

    @Override
    protected boolean isFinished() {
        return liftDone;
    }

    @Override
    protected void end() {
    	Robot.Lift.liftLock();
    }

    @Override
    protected void interrupted() {
    }
}