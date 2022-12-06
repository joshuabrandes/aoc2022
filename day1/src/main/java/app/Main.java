package app;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var input = readFile("src/main/resources/inputValues.txt");
        var list2D = splitArrayAtEmptyLines(input);
        var caloriesList = getTotalForEachListElement(list2D);
        var maximum = getMaximumAndIndex(caloriesList);

        System.out.println("Total dwarves: " + caloriesList.size());
        System.out.println("Highest calories count: " + maximum.getRight() + " (Index: " + maximum.getLeft() + ")");
    }

    static List<String> readFile(String filePath) {
        var lineList = new ArrayList<String>();
        try {
            var file = new File(filePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lineList.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lineList;
    }

    static List<List<Integer>> splitArrayAtEmptyLines(List<String> input) {
        var result = new ArrayList<List<Integer>>();
        var tmpList = new ArrayList<Integer>();

        for (String field : input) {
            if (field != null && !field.isEmpty() && !field.isBlank()) {
                tmpList.add(Integer.parseUnsignedInt(field));
            } else {
                result.add(tmpList);
                tmpList = new ArrayList<>();
            }
        }

        return result;
    }

    static List<Integer> getTotalForEachListElement(List<List<Integer>> input) {
        var result = new ArrayList<Integer>();

        for (List<Integer> field : input) {
            var sum = 0;

            for (Integer integer : field) {
                sum += integer;
            }
            result.add(sum);
        }

        return result;
    }

    static Pair<Integer, Integer> getMaximumAndIndex(List<Integer> input) {
        var maxIndex = 0;
        var maxValue = 0;

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) > maxValue) {
                maxIndex = i;
                maxValue = input.get(i);
            }
        }

        return Pair.of(maxIndex, maxValue);
    }
}
