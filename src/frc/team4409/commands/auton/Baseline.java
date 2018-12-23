package frc.team4409.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4409.commands.DriveStraightTime;

public class Baseline extends CommandGroup {

    public Baseline(){
        addSequential(new DriveStraightTime(0.5,1));
    }
}
