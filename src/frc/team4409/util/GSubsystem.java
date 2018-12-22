package frc.team4409.util;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class GSubsystem extends Subsystem {

    private Notifier loopNotifier = new Notifier(new Runnable() {
        public void run() {
            loop();
        }
    });

    public void startLooping() {
        loopNotifier.startPeriodic(0.02);
    }

    public abstract void loop();

    public abstract void stop();
}
