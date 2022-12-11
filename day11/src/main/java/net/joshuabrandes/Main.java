package net.joshuabrandes;

import net.joshuabrandes.data.DataProvider;
import net.joshuabrandes.model.Monkey;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var monkeys = DataProvider.monkeyList;

        for (int i = 0; i < 20; i++) {
            iteration(monkeys);
        }

        printMonkeyList(monkeys);

        var sortedMonkeys = monkeys.stream()
                .sorted(Comparator.comparingInt(Monkey::getNumberOfInspections))
                .toList();

        printMonkeyList(sortedMonkeys);

        var importantMonkey1 = sortedMonkeys.get(sortedMonkeys.size() - 1);
        var importantMonkey2 = sortedMonkeys.get(sortedMonkeys.size() - 2);

        var monkeyBusiness = importantMonkey1.getNumberOfInspections() * importantMonkey2.getNumberOfInspections();
        System.out.println("\n\n" + "Level of monkey Business: " + monkeyBusiness);
    }

    private static void iteration(List<Monkey> monkeys) {
        var itemsToMove = new ArrayList<Pair<Integer, Integer>>();

        for (Monkey element : monkeys) {
            itemsToMove.addAll(element.inspectItems());
        }

        for (Monkey element : monkeys) {
            var itemsToAdd = new ArrayList<Integer>();

            for (Pair<Integer, Integer> item : itemsToMove) {
                if (item.getKey() == element.getId()) {
                    itemsToAdd.add(item.getValue());
                }
            }

            element.setItems(itemsToAdd);
            /*
            element.setNumberOfInspections(
                    element.getNumberOfInspections() + itemsToAdd.size());
             */
            itemsToAdd.clear();
        }
    }

    private static void printMonkeyList(List<Monkey> monkeys) {
        for (Monkey element : monkeys) {
            System.out.println("Monkey " +
                    element.getId() + " inspected items " +
                    element.getNumberOfInspections() + " times.");
        }
        System.out.println("\n");
    }
}