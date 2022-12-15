package net.joshuabrandes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeaconExclusionZone {
    public static void main(String[] args) throws FileNotFoundException {
        // read input
        Scanner scanner = null;
        List<Sensor> sensors = null;
        try {
            scanner = new Scanner(new File("src/main/resources/input.txt"));
            Pattern pattern = Pattern.compile("-?\\d+");
            sensors = extractCoordinatesFromInput(scanner, pattern);
        } catch (FileNotFoundException | IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            assert scanner != null;
            scanner.close();
        }

        System.out.println(sensors.size());

            /* Example input data
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
             */

        // Find the beacons that are not covered by any sensor
        List<Beacon> uncoveredBeacons = findUncoveredBeacons(sensors);
        System.out.println(uncoveredBeacons.size());

        // Print the coordinates of the uncovered beacons
        for (Beacon beacon : uncoveredBeacons) {
            System.out.println("Uncovered beacon at x=" + beacon.getX() + ", y=" + beacon.getY());
        }
    }

    private static List<Sensor> extractCoordinatesFromInput(Scanner scanner, Pattern pattern) throws IllegalAccessException {
        Matcher matcher;

        List<Sensor> sensors = new ArrayList<>();
        var lines = new ArrayList<String>();

        while (scanner.hasNext()) {
            var line = scanner.nextLine();
            lines.add(line);
        }

        System.out.println(lines.size());

        for (String line : lines) {
            System.out.println(line);
            matcher = pattern.matcher(line);

            List<Integer> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
            if (numbers.size() == 4) {
                sensors.add(new Sensor(
                        numbers.get(0),
                        numbers.get(1),
                        numbers.get(2),
                        numbers.get(3)
                ));
            }else throw new IllegalAccessException();
        }
        return sensors;
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

    static class Sensor {
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

    static class Beacon {
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
}
