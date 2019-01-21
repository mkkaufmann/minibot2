package frc.team4409.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4409.util.Controller;

public class Rumble extends Command {

    private boolean mLeft;
    private boolean mRight;
    private double mTime;
    private Controller controller;

    private Rumble(){
        controller = RobotMap.controller;
    }

    public Rumble(boolean left, double time){
        this();
        mLeft = left;
        mRight = !left;
        mTime = time;
    }

    public Rumble(double time){
        this();
        mLeft = true;
        mRight = true;
        mTime = time;
    }

    @Override
    protected void initialize() {
        setTimeout(mTime);
    }

    @Override
    public void start(){
        super.start();
        if(mLeft){
            controller.startLRumble();
        }
        if(mRight){
            controller.startRRumble();
        }
    }

    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished(){
        return isTimedOut();
    }

    @Override
    protected void end() {
        if(mLeft){
            controller.endLRumble();
        }
        if(mRight){
            controller.endRRumble();
        }
    }

    @Override
    protected void interrupted(){
        end();
    }
}
