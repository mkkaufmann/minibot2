package frc.team4409.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4409.subsystems.Drive;

public class DriveTime extends Command {

    private Drive drive = Drive.getInstance();
    private double mLeft;
    private double mRight;
    private double mTime;

    public DriveTime(double left, double right, double time){
        requires(drive);
        mLeft = left;
        mRight = right;
        mTime = time;
    }

    @Override
    protected void initialize() {
        setTimeout(mTime);
    }

    @Override
    protected void execute() {
        drive.tank(mLeft, mRight);
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
