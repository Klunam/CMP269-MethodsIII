package Assigments.Excersice2;

public abstract class SmartDevice implements Powerable {
    protected String deviceName;
    protected boolean isOn;

    public static int activeDevicesCount = 0;

    public SmartDevice(String deviceName){
        this.deviceName = deviceName;
        this.isOn = false;
    }

    public abstract void performSelfDiagnostic();

    @Override
    public void turnOn(){
        if(!isOn){
            isOn = true;
            activeDevicesCount++;
        }
    }

    @Override
    public void turnOff(){
        if (isOn){
            isOn = false;
            activeDevicesCount--;
        }
    }
    
}
