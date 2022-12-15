package net.joshuabrandes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeaconExclusionZone {
    public static void main(String[] args) {
        // Example input data
        List<Sensor> sensors = new ArrayList<>();
        sensors.add(new Sensor(2, 18, -2, 15));
        sensors.add(new Sensor(9, 16, 10, 16));
        sensors.add(new Sensor(13, 2, 15, 3));
        sensors.add(new Sensor(12, 14, 10, 16));
        sensors.add(new Sensor(10, 20, 10, 16));
        sensors.add(new Sensor(14, 17, 10, 16));
        sensors.add(new Sensor(8, 7, 2, 10));
        sensors.add(new Sensor(2, 0, 2, 10));
        sensors.add(new Sensor(0, 11, 2, 10));
        sensors.add(new Sensor(20, 14, 25, 17));
        sensors.add(new Sensor(17, 20, 21, 22));
        sensors.add(new Sensor(16, 7, 15, 3));
        sensors.add(new Sensor(14, 3, 15, 3));
        sensors.add(new Sensor(20, 1, 15, 3));

        // Find the beacons that are not covered by any sensor
        List<Beacon> uncoveredBeacons = findUncoveredBeacons(sensors);

        // Print the coordinates of the uncovered beacons
        for (Beacon beacon : uncoveredBeacons) {
            System.out.println("Uncovered beacon at x=" + beacon.getX() + ", y=" + beacon.getY());
        }
    }

    private static List<Beacon> findUncoveredBeacons(List<Sensor> sensors) {
        // Map beacons to the sensors that are closest to them
        Map<Beacon, Sensor> beaconCoverage = new HashMap<>();
        for (Sensor sensor : sensors) {
            Beacon closestBeacon = sensor.getClosestBeacon();
            beaconCoverage.put(closestBeacon, sensor);
        }

        // Find the beacons that are not covered by any sensor
        List<Beacon> uncoveredBeacons = new ArrayList<>();
        for (Sensor sensor : sensors) {
            Beacon closestBeacon = sensor.getClosestBeacon();
            if (!beaconCoverage.containsKey(closestBeacon)) {
                uncoveredBeacons.add(closestBeacon);
            }
        }
        return uncoveredBeacons;
    }
}

class Sensor {
    private int x;
    private int y;
    private Beacon closestBeacon;

    public Sensor(int x, int y, int beaconX, int beaconY) {
        this.x = x;
        this.y = y;
        this.closestBeacon = new Beacon(beaconX, beaconY);
    }

    public Beacon getClosestBeacon() {
        return closestBeacon;
    }
}

class Beacon {
    private int x;
    private int y;

    public Beacon(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beacon beacon = (Beacon) o;
        return x == beacon.x &&
                y == beacon.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}

