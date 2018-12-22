package frc.team4409.util;

public class LatchedBoolean {
    private boolean mLast = false;

    public boolean update(boolean newValue){
        boolean val = false;

        if(newValue && !mLast){
            val = true;
        }

        mLast = newValue;

        return val;
    }
}
