/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4409.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4409.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drive extends Subsystem
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private static Drive mDrive;

    public static Drive getInstance(){
        if(mDrive != null){
            mDrive = new Drive();
        }
        return mDrive;
    }

    private Drive(){
    }

    private DriveStates driveState = DriveStates.PARK;

    public void shiftGear(){
        if(driveState == DriveStates.PARK){
            driveState = DriveStates.HALF_SPEED;
        }else if(driveState == DriveStates.HALF_SPEED){
            driveState = DriveStates.FULL_SPEED;
        }else if(driveState == DriveStates.FULL_SPEED){
            driveState = DriveStates.PARK;
        }
    }
    public void shiftGear(DriveStates state){
        driveState = state;
    }
    public enum DriveStates {
        PARK,
        HALF_SPEED,
        FULL_SPEED
    }
    public void drive(){
        double speedFactor = 0;
        if(driveState == DriveStates.HALF_SPEED){
            speedFactor = 0.5;
        }else if(driveState == DriveStates.FULL_SPEED){
            speedFactor = 0.5;
        }
        RobotMap.left.set(ControlMode.PercentOutput, RobotMap.controller.getX() * speedFactor);
        RobotMap.right.set(ControlMode.PercentOutput, RobotMap.controller.getY() * speedFactor);
    }
    public void initDefaultCommand() 
    {
        // TODO: Set the default command for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
}
