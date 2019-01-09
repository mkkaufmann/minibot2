/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4409;

import edu.wpi.first.wpilibj.DriverStation;
import frc.team4409.subsystems.Claw;
import frc.team4409.subsystems.Drive;
import frc.team4409.util.Controller;
import frc.team4409.util.GSubsystem;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI extends GSubsystem
{
    private static OI mInstance;

    private static Controller controller;
    private static Drive drive;
    private static Claw claw;

    public static OI getInstance(){
        if(mInstance == null){
            mInstance = new OI();
        }
        return mInstance;
    }
    private OI(){
        controller = RobotMap.driver;
        drive = Drive.getInstance();
        claw = Claw.getInstance();
    }



    @Override
    protected void initDefaultCommand() {

    }

    public boolean isAuto(){
        return DriverStation.getInstance().isAutonomous() && isEnabled();
    }

    public boolean isEnabled() {
        return DriverStation.getInstance().isEnabled();
    }


    public boolean isTest(){
        return DriverStation.getInstance().isTest();
    }

    public boolean isTele() {
        return !isAuto() && !isTest() && isEnabled();
    }

    private boolean teleopInitialized = false;
    private boolean isDisabledInitialized = true;

    @Override
    public void loop(){
        if(isTele()){
            isDisabledInitialized = false;
            if(!teleopInitialized){
                drive.setWantedState(Drive.DriveState.OPEN_LOOP_DRIVER);
                teleopInitialized = true;
            }
            if(controller.isAPressed()){
                System.out.println("a");
                drive.setIsSlow(!drive.isSlow());
            }
            if(controller.isXPressed()){
                System.out.println("x");
                if(drive.getDriveState() == Drive.DriveState.NEUTRAL){
                    drive.setWantedState(Drive.DriveState.OPEN_LOOP_DRIVER);
                }else{
                    drive.setWantedState(Drive.DriveState.NEUTRAL);
                }
            }
            if(controller.isRBPressed()){
                claw.toggle();
            }
        }else{
            if(!isDisabledInitialized){
                drive.setWantedState(Drive.DriveState.NEUTRAL);
                isDisabledInitialized = true;
            }
            teleopInitialized = false;
        }
    }

    @Override
    public void in() {

    }

    @Override
    public void out() {

    }


    @Override
    public void stop(){
        drive.setWantedState(Drive.DriveState.NEUTRAL);
    }
    // CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    // joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}
