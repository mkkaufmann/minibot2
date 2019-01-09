package frc.team4409.subsystems;

import edu.wpi.first.wpilibj.Servo;
import frc.team4409.RobotMap;
import frc.team4409.util.GSubsystem;

public class Claw extends GSubsystem {

    private static Servo servo;
    private static Claw mClaw = null;
    private static ClawState mClawState = ClawState.CLOSED;
    private static ClawState mWantedClawState = ClawState.OPEN;

    public static Claw getInstance(){
        if(mClaw == null){
            mClaw = new Claw();
            servo = RobotMap.claw;
        }
        return mClaw;
    }

    private Claw(){
    }

    public void toggle(){
        if(mClawState == ClawState.OPEN){
            setWantedState(ClawState.CLOSED);
        }else{
            setWantedState(ClawState.OPEN);
        }
    }

    @Override
    protected void initDefaultCommand(){

    }

    @Override
    public void loop(){
        in();
        setState();
        out();
    }

    @Override
    public void in() {
    }

    public enum ClawState {
        OPEN,
        CLOSED
    }

    public static void setWantedState(ClawState wantedState) {
        mWantedClawState = wantedState;
    }

    private void setState(ClawState state){
        mWantedClawState = state;
    }

    private void setState(){
        mClawState = mWantedClawState;
    }

    @Override
    public void out(){
        if(mClawState == ClawState.OPEN){
            servo.setAngle(120);
        }else{
            servo.setAngle(60);
        }

    }

    @Override
    public void stop(){

    }
}
