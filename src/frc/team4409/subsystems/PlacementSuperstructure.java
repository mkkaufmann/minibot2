package frc.team4409.subsystems;

import frc.team4409.util.GSubsystem;

public class PlacementSuperstructure extends GSubsystem{

    private static state superstructureState;
    private static PlacementSuperstructure mPlacementSuperstructure = null;

    private static PlacementSuperstructure getInstance(){
        if(mPlacementSuperstructure == null){
            mPlacementSuperstructure = new PlacementSuperstructure();
        }
        return mPlacementSuperstructure;
    }

    private PlacementSuperstructure(){

    }

    public enum state{
        HATCH,
        CARGO
    }

    public static state getSuperstructureState(){
        return superstructureState;
    }

}