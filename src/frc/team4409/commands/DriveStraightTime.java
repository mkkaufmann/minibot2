package frc.team4409.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4409.subsystems.Drive;

public class DriveStraightTime extends DriveTime {

    private Drive drive = Drive.getInstance();

    public DriveStraightTime(double speed, double time){
        super(speed, speed, time);
        requires(drive);
    }

}
