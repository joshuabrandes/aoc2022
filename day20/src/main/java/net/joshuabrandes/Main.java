package net.joshuabrandes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read the input file
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"));
        List<Integer> numbers = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            numbers.add(Integer.parseInt(line));
        }
        reader.close();

        // Mix the numbers
        int[] mixedNumbers = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            int offset = numbers.get(i);
            int destinationIndex = (i + offset) % numbers.size();
            if (destinationIndex < 0) {
                destinationIndex += numbers.size();
            }
            mixedNumbers[destinationIndex] = numbers.get(i);
        }

        // Find the grove coordinates
        int groveCoordinate1 = mixedNumbers[1000 % numbers.size()];
        int groveCoordinate2 = mixedNumbers[2000 % numbers.size()];
        int groveCoordinate3 = mixedNumbers[3000 % numbers.size()];

        // Calculate the sum of the grove coordinates
        int sum = groveCoordinate1 + groveCoordinate2 + groveCoordinate3;
        System.out.println(sum);
    }
}
