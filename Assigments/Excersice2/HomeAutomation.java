package Assigments.Excersice2;
// Main.java file

import java.util.ArrayList;

public class HomeAutomation {
    public static void main(String[] args) {

        ArrayList<SmartDevice> homeHub = new ArrayList<>();

        //devices
        SmartLight livingRoomLight = new SmartLight("Living Room Light");
        SmartLight kitchenLight = new SmartLight("Kitchen Light");
        SmartThermostat hallwayStat = new SmartThermostat("Hallway Thermostat");

        homeHub.add(livingRoomLight);
        homeHub.add(kitchenLight);
        homeHub.add(hallwayStat);

        // The Logic Test
        livingRoomLight.turnOn();
        hallwayStat.turnOn();

        kitchenLight.setLevel(75);

     
        System.out.println("\nTotal Active Devices: " + SmartDevice.activeDevicesCount);

        // Polymorphism ( Loop through the homeHub and call performSelfDiagnostic() on every device)
        System.out.println("\n--- Running Diagnostics ---");
        for (SmartDevice device : homeHub) {
            device.performSelfDiagnostic();
        }
    }
}