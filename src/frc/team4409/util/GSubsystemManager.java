package frc.team4409.util;

import java.util.List;

public class GSubsystemManager {
    private List<GSubsystem> mAllSubsystems;
    public GSubsystemManager(List<GSubsystem> allSubsystems){
        mAllSubsystems = allSubsystems;
    }
    public void startLooping(){
        mAllSubsystems.forEach(((subsystem) -> subsystem.startLooping()));
    }

    public void loop(){mAllSubsystems.forEach(((subsystem) -> subsystem.loop())); }

    public void stop(){
        mAllSubsystems.forEach(((subsystem) -> subsystem.stop()));
    }

    public List<GSubsystem> getAllSubsystems(){
        return mAllSubsystems;
    }
}
