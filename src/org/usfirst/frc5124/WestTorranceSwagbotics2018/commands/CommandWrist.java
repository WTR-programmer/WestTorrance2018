package org.usfirst.frc5124.WestTorranceSwagbotics2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2018.RobotMap;

public class CommandWrist extends Command {

	private double pos;
	private boolean done;
	
    public CommandWrist(double ticks) {
    	pos = ticks;
    }

    @Override
    protected void initialize() {
    	done = false;
    	Robot.intake.flipPID.setAbsoluteTolerance(.5);
    }

    @Override
    protected void execute() {
    	Robot.intake.flipPID.enable();
    	Robot.intake.setFlipperSetpoint(pos);
    		
    		if (Robot.intake.flipPID.onTarget()) {
    			done = true;
    		}
    		else {
    			done = false;
    		}
    	
    }

    @Override
    protected boolean isFinished() {
        return done;
    }

    @Override
    protected void end() {
    	Robot.intake.flipPID.disable();
    }

    @Override
    protected void interrupted() {
    }
}