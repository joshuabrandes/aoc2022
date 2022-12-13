package net.joshuabrandes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner to read input from the user
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/main/resources/input.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found at location: " +
                    ex.getMessage() +
                    "\n\n");
            ex.printStackTrace();
        }

        if (scanner == null) {
            return;
        }

        // Read in the pairs of packets
        List<String[]> pairs = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] pair = new String[2];
            pair[0] = scanner.nextLine();
            pair[1] = scanner.nextLine();
            pairs.add(pair);
        }

        // Keep track of the number of pairs that are in the right order
        int numRight = 0;

        // Loop through each pair and determine if they are in the right order
        for (String[] pair : pairs) {
            if (isRightOrder(pair[0], pair[1])) {
                numRight++;
            }
        }

        // Print the number of pairs that are in the right order
        System.out.println(numRight);
    }

    public static boolean isRightOrder(String left, String right) {
        // Create scanners to read each packet
        Scanner leftScanner = new Scanner(left);
        Scanner rightScanner = new Scanner(right);

        // Loop until we reach the end of one of the packets
        while (leftScanner.hasNext() && rightScanner.hasNext()) {
            // Read the next value from each packet
            String leftValue = leftScanner.next();
            String rightValue = rightScanner.next();

            // Check if the left value is a list
            if (leftValue.startsWith("[")) {

            }
        }
        // TODO!!!!
        return true;
    }
}
