package Assigments.Excersice2;
public class SmartLight extends SmartDevice implements Adjustable {
    private int brightness;
    
    public SmartLight(String deviceName) {
        super(deviceName);
    }

    @Override
    public void setLevel(int level) {
        if (!isOn) {
            System.out.println(deviceName + " - Cannot adjust: Device is OFF.");
        } else {
            this.brightness = Math.min(100, Math.max(0, level));
            System.out.println(deviceName + " brightness set to " + this.brightness + "%.");
        }
    }

    @Override
    public void performSelfDiagnostic() {
        System.out.println(deviceName + ": Checking LED health...");
    }
}