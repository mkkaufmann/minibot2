package frc.team4409.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class Controller extends Joystick {

    private LatchedBoolean a = new LatchedBoolean();
    private LatchedBoolean b = new LatchedBoolean();
    private LatchedBoolean y = new LatchedBoolean();
    private LatchedBoolean x = new LatchedBoolean();
    private LatchedBoolean lb = new LatchedBoolean();
    private LatchedBoolean rb = new LatchedBoolean();

    public Controller(int port){
        super(port);
    }

    public double getLeftX(){
        return this.getRawAxis(0);
    }

    public double getLeftY(){
        return this.getRawAxis(1);
    }

    public double getRightX(){
        return this.getRawAxis(4);
    }

    public double getRightY(){
        return this.getRawAxis(5);
    }

    public boolean isAPressed(){
        return a.update(this.getRawButton(Buttons.A));
    }

    public boolean isBPressed(){
        return b.update(this.getRawButton(Buttons.B));
    }

    public boolean isXPressed(){
        return x.update(this.getRawButton(Buttons.X));
    }

    public boolean isYPressed(){
        return y.update(this.getRawButton(Buttons.Y));
    }

    public boolean isLBPressed(){
        return lb.update(this.getRawButton(Buttons.LB));
    }

    public boolean isRBPressed(){
        return rb.update(this.getRawButton(Buttons.RB));
    }

    public void startLRumble(){
        this.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
    }

    public void startRRumble(){
        this.setRumble(GenericHID.RumbleType.kRightRumble, 1);
    }

    public void endLRumble(){
        this.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
    }

    public void endRRumble(){
        this.setRumble(GenericHID.RumbleType.kRightRumble, 0);
    }

    public void startRumble(){
        this.startLRumble();
        this.startRRumble();
    }

    public void endRumble(){
        this.endLRumble();
        this.endRRumble();
    }

    private static class Buttons{
        public static int A = 1;
        public static int B = 2;
        public static int X = 3;
        public static int Y = 4;
        public static int LB = 5;
        public static int RB = 6;
    }

}
