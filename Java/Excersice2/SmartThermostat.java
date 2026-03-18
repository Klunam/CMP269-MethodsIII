package Assigments.Excersice2;

public class SmartThermostat extends SmartDevice implements Adjustable {
    private int temperature;

    public SmartThermostat(String deviceName) {
        super(deviceName);
    }

    @Override
    public void turnOn() {
        System.out.println(".... System Starting...");
        super.turnOn();
    }

    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println(deviceName + " - Cannot adjust: Device is OFF.");
        } else if (level < 60 || level > 80) {
            System.out.println("Invalid temperature. Range is 60-80.");
        } else {
            this.temperature = level;
            System.out.println(deviceName + " temperature set to " + this.temperature + " degrees.");
        }
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println(deviceName + ": Testing thermostat sensors...");
    }
}