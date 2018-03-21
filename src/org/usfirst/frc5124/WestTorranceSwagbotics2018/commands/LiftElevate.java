package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class LiftElevate extends Command {

	private double ticksToMove;
	private double ticksMoved;
	private double power;
	private boolean done;
	private double currentPos = 0;
	
    public LiftElevate(double ticks) {
    	ticksToMove = ticks;
    }

    @Override
    protected void initialize() {
    	done = false;
    }

    @Override
    protected void execute() {
    	if (Math.abs(ticksMoved) < Math.abs(ticksToMove)) {
    		Robot.Lift.liftUp();
    		done = false;
    	} else {
    		Robot.Lift.liftLock();
    		done = true;
    		
    		currentPos += RobotMap.liftEncoder.get();
    	}
    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}