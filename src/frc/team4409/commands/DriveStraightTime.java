package frc.team4409.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4409.subsystems.Drive;

public class DriveStraightTime extends Command {

    private Drive drive = Drive.getInstance();
    private double mSpeed;
    private double mTime;

    public DriveStraightTime(double speed, double time){
        requires(drive);
        mSpeed = speed;
        mTime = time;
    }

    @Override
    protected void initialize() {
        setTimeout(mTime);
    }

    @Override
    protected void execute() {
        drive.tank(mSpeed, mSpeed);
    }

    @Override
    protected boolean isFinished(){
        return isTimedOut();
    }

    @Override
    protected void end() {
        drive.stop();
    }

    @Override
    protected void interrupted(){
        end();
    }
}
