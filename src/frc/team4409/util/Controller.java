package frc.team4409.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class Controller extends Joystick {

    private LatchedBoolean a = new LatchedBoolean();
    private LatchedBoolean b = new LatchedBoolean();
    private LatchedBoolean y = new LatchedBoolean();
    private LatchedBoolean x = new LatchedBoolean();

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

    private static class Buttons{
        public static int A = 1;
        public static int B = 2;
        public static int X = 3;
        public static int Y = 4;
    }

}
