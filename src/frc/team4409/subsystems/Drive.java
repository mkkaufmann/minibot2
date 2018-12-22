/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4409.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4409.Robot;
import frc.team4409.RobotMap;
import frc.team4409.util.GSubsystem;

/**
 * Drive subsystem
 */
public class Drive extends GSubsystem
{
    //TODO: COMMENT EVERYTHING
    private static Drive mDrive;

    public static Drive getInstance(){
        if(mDrive == null){
            mDrive = new Drive();
        }
        return mDrive;
    }

    private TalonSRX L;
    private TalonSRX R;
    private Drive(){
        L = RobotMap.left;
        R = RobotMap.right;
    }

    @Override
    protected void initDefaultCommand() {
    }

    private static DriveStates driveState = DriveStates.PARK;

    public static void shiftGear(){
        if(driveState == DriveStates.PARK){
            driveState = DriveStates.HALF_SPEED;
        }else if(driveState == DriveStates.HALF_SPEED){
            driveState = DriveStates.FULL_SPEED;
        }else if(driveState == DriveStates.FULL_SPEED){
            driveState = DriveStates.PARK;
        }
        System.out.println(driveState);
    }
    public static void shiftGear(DriveStates state){
        driveState = state;
        System.out.println(driveState);
    }

    public enum DriveStates {
        PARK,
        HALF_SPEED,
        FULL_SPEED
    }

    @Override
    public void loop(){
        drive();
    }

    @Override
    public void stop(){

    }

    public void drive(){
        double speedFactor = 0;
        if(driveState == DriveStates.HALF_SPEED){
            speedFactor = 0.5;
        }else if(driveState == DriveStates.FULL_SPEED){
            speedFactor = 1;
        }
        L.set(ControlMode.PercentOutput, -RobotMap.driver.getLeftY() * speedFactor);
        R.set(ControlMode.PercentOutput, RobotMap.driver.getRightY() * speedFactor);
    }
}
