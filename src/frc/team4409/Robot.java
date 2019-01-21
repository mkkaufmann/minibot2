/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4409;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4409.commands.DriveStraightTime;
import frc.team4409.commands.Rumble;
import frc.team4409.commands.auton.Baseline;
import frc.team4409.subsystems.Claw;
import frc.team4409.subsystems.Drive;
import frc.team4409.util.GSubsystemManager;
import frc.team4409.commands.Rumble;

import java.util.Arrays;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
// If you rename or move this class, update the build.properties file in the project root
public class Robot extends TimedRobot 
{

    public static final Drive exampleSubsystem = Drive.getInstance();
    public static OI oi;
    public static GSubsystemManager allSubsystems = new GSubsystemManager(Arrays.asList(Drive.getInstance(), OI.getInstance(), Claw.getInstance()));

    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<Command>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() 
    {
        oi = OI.getInstance();
        chooser.addDefault("Baseline", new Baseline());
        SmartDashboard.putData("Auto mode", chooser);

    }

    @Override
    public void robotPeriodic(){
        allSubsystems.loop();
    }
    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() 
    {
        allSubsystems.stop();
    }

    @Override
    public void disabledPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() 
    {
        autonomousCommand = chooser.getSelected();


        // schedule the autonomous command (example)
        if (autonomousCommand != null) 
        {
            System.out.println("started");
            autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() 
    {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) 
        {
            autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() 
    {
        
    }
}
