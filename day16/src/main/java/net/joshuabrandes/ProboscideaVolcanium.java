package net.joshuabrandes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProboscideaVolcanium {

    static final String regex = "Valve\\s+(\\w+)\\s+has\\s+flow\\s+rate=(\\d+);\\s+(tunnels|tunnel)\\s+lead(?:s)?\\s+to\\s+valves?\\s+(\\w+(?:,\\s\\w+)*)?";


    private static class Valve {
        String name;
        int flowRate;
        List<String> tunnels;

        public Valve(String name, int flowRate, List<String> tunnels) {
            this.name = name;
            this.flowRate = flowRate;
            this.tunnels = tunnels;
        }
    }

    public static int getMaxPressureReleased(List<Valve> valves) {
        Map<String, Valve> valveMap = new HashMap<>();
        for (Valve valve : valves) {
            valveMap.put(valve.name, valve);
        }

        int maxPressureReleased = 0;
        for (Valve startValve : valves) {
            if (startValve.flowRate == 0) {
                continue;
            }
            int pressureReleased = dfs(valveMap, startValve, new ArrayList<>(), 0, 30);
            maxPressureReleased = Math.max(maxPressureReleased, pressureReleased);
        }

        return maxPressureReleased;
    }

    private static int dfs(Map<String, Valve> valveMap, Valve valve, List<String> visited, int minutesSpent, int timeLimit) {
        visited.add(valve.name);

        int pressureReleased = 0;
        for (String nextValveName : valve.tunnels) {
            if (visited.contains(nextValveName)) {
                continue;
            }
            Valve nextValve = valveMap.get(nextValveName);
            int remainingTime = timeLimit - minutesSpent - 1;
            if (remainingTime < 1) {
                continue;
            }
            pressureReleased = Math.max(pressureReleased, dfs(valveMap, nextValve, visited, minutesSpent + 1, timeLimit));
        }
        visited.remove(visited.size() - 1);

        return pressureReleased + valve.flowRate * Math.min(timeLimit - minutesSpent - 1, valve.flowRate);
    }

    public static void main(String[] args) {

        /* Example
        List<Valve> valves = new ArrayList<>();
        valves.add(new Valve("AA", 0, List.of("DD", "II", "BB")));
        valves.add(new Valve("BB", 13, List.of("CC", "AA")));
        valves.add(new Valve("CC", 2, List.of("DD", "BB")));
        valves.add(new Valve("DD", 20, List.of("CC", "AA", "EE")));
        valves.add(new Valve("EE", 3, List.of("FF", "DD")));
        valves.add(new Valve("FF", 0, List.of("EE", "GG")));
        valves.add(new Valve("GG", 0, List.of("FF", "HH")));
        valves.add(new Valve("HH", 22, List.of("GG")));
        valves.add(new Valve("II", 0, List.of("AA", "JJ")));
        valves.add(new Valve("JJ", 21, List.of("II")));

         */

        var valves = readInputToValves();

        int maxPressureReleased = getMaxPressureReleased(valves);
        System.out.println("Max pressure released: " + maxPressureReleased);
    }

    private static List<Valve> readInputToValves() {
        var valves = new ArrayList<Valve>();
        var inputSize = 0;

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/main/resources/input.txt"));
            while (scanner.hasNextLine()) {
                inputSize++;
                var line = scanner.nextLine().trim();

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    String valveName = matcher.group(1);
                    int flowRate = Integer.parseInt(matcher.group(2));
                    // group 3 is (tunnels|tunnel)
                    String[] tunnelValves = matcher.group(4).split(",\\s");

                    valves.add(new Valve(valveName, flowRate, Arrays.asList(tunnelValves)));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            assert scanner != null;
            scanner.close();
        }

        assert valves.size() == inputSize;
        return valves;
    }
}

