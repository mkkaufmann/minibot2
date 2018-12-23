/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4409.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team4409.OI;
import frc.team4409.RobotMap;
import frc.team4409.util.Controller;
import frc.team4409.util.GSubsystem;

import static frc.team4409.Robot.oi;

/**
 * Drive subsystem
 */
public class Drive extends GSubsystem
{
    //TODO: COMMENT EVERYTHING

    private static Drive mDrive;
    private TalonSRX L;
    private TalonSRX R;
    private static DriveState mWantedDriveState = DriveState.NEUTRAL;
    private static DriveState mDriveState = DriveState.NEUTRAL;
    private static double mWantedLeftOutput;
    private static double mWantedRightOutput;
    private static double mLeftOutput;
    private static double mRightOutput;
    private static ControlMode mControlMode = ControlMode.Disabled;
    private static boolean mIsSlow = true;
    private static double mModifyPercent = 1;

    public static Drive getInstance(){
        if(mDrive == null){
            mDrive = new Drive();
        }
        return mDrive;
    }

    private Drive(){
        L = RobotMap.left;
        R = RobotMap.right;
    }

    public void setIsSlow(boolean isSlow){
        mIsSlow = isSlow;
    }

    public boolean isSlow(){
        return mIsSlow;
    }

    public DriveState getDriveState(){
        return mDriveState;
    }

    public enum DriveState {
        NEUTRAL,
        OPEN_LOOP,
        OPEN_LOOP_DRIVER
    }

    public synchronized void setWantedState(DriveState wantedState){
        mWantedDriveState = wantedState;
    }

    private synchronized void switchToState(DriveState state){
        if(mDriveState != state){
            onStateExit(mDriveState);
            mDriveState = state;
            onStateStart(mDriveState);
        }
    }

    private synchronized void handleStates(){
        switchToState(mWantedDriveState);
    }

    private synchronized void onStateExit(DriveState exitingState){
        //TODO: make this do something
        switch(exitingState){
            case NEUTRAL:
                break;
            case OPEN_LOOP:
                break;
            case OPEN_LOOP_DRIVER:
                break;
        }
    }

    private synchronized void onStateStart(DriveState startingState){
        switch(startingState){
            case NEUTRAL:
                brake(NeutralMode.Brake);
                break;
            case OPEN_LOOP:
                brake(NeutralMode.Brake);
                break;
            case OPEN_LOOP_DRIVER:
                brake(NeutralMode.Coast);
                break;
        }
    }

    private synchronized void brake(NeutralMode mode) {
        L.setNeutralMode(mode);
        R.setNeutralMode(mode);
    }

    @Override
    public synchronized void loop(){
        handleStates();
        in();
        out();
    }

    @Override
    public synchronized void in(){
        mModifyPercent = mIsSlow ? .5 : 1;
    }

    @Override
    public synchronized void out(){
        switch(mDriveState){
            case NEUTRAL:
                mControlMode = ControlMode.Disabled;
                mLeftOutput = 0;
                mRightOutput = 0;
                break;
            case OPEN_LOOP:
                mControlMode = ControlMode.PercentOutput;
                mLeftOutput = mWantedLeftOutput;
                mRightOutput = mWantedRightOutput;
                break;
            case OPEN_LOOP_DRIVER:
                tank(RobotMap.driver);
                mControlMode = ControlMode.PercentOutput;
                mLeftOutput = mWantedLeftOutput;
                mRightOutput = mWantedRightOutput;
                break;
            default:
                System.out.println("unknown drive state");
                break;
        }
        L.set(mControlMode, -mLeftOutput);
        R.set(mControlMode, mRightOutput);
    }

    public synchronized void tank(Controller controller){
        tankNoState(controller.getLeftY(), controller.getRightY());
    }

    public synchronized void tankNoState(double left, double right){
        mWantedLeftOutput = left * mModifyPercent;
        mWantedRightOutput = right * mModifyPercent;
    }

    public synchronized void tank(double left, double right){
        setWantedState(DriveState.OPEN_LOOP);
        tankNoState(left, right);
    }

    @Override
    public synchronized void stop(){
        setWantedState(DriveState.NEUTRAL);
    }


    @Override
    protected void initDefaultCommand() {
    }
}
