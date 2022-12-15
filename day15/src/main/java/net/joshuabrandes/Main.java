package net.joshuabrandes;

public class Main {
    public static void main(String[] args) {
        //parse the inbut data using a regular expression
Pattern pattern = Pattern.compile("Sensor at x=(-?||d+), y=(-? | Id+): closest
beacon is at X=(-?\Id+), y-(-?| Id+)");
Matcher matcher = pattern.matcher(input);
I create a list to store the coordinates of the sensors and beacons
List‹Coordinate> sensors = new ArrayList<>();
List<Coordinate> beacons = new ArrayList<>O
I iterate through the input data and extract the coordinates of the sensors and
beacons
while (matcher.find()) {
int sensor = Integer.parseInt (matcher. group (1));
int sensory = Integer.parseInt(matcher.group (2));
int beacon = Integer.parseInt (matcher. group(3));
int beacon = Integer-parseInt (matcher. group (4));
sensors.add (new Coordinate(sensorx, sensorY));
beacons. add(new Coordinate(beaconX, beaconY));
/ compute the distance between each sensor and its nearest beacon
for (Coordinate sensor : sensors) {
int minbistance = Integer.MAX VALUE:
Coordinate nearestBeacon
for (Coordinate beacon : beacons) <
int distance = Math.abs(sensor x - beacon.x) + Math.abs (sensor.y
beacon.y);
if (distance < minDistance) {
minDictance = distance
nearestBeacon = beacon;
/ print the coordinates of the nearest beacon for this sensor
System.out-printIn("Nearest beacon for sensor at nearestBeacon);
    }
}