package net.joshuabrandes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Monkey {

    final int id;

    List<Integer> items;
    final Operations operation;
    final int modifier;
    final int divisor;
    final int monkeyWhenTrue;
    final int monkeyWhenFalse;
    int numberOfInspections = 0;

    /**
     * inspect all items
     *
     * @return List of Pairs containing the target monkey (left) and value f the item (right)
     */
    public List<Pair<Integer, Integer>> inspectItems() {
        List<Pair<Integer, Integer>> inspectedItems = new ArrayList<>();

        for (Integer item : items) {
            inspectedItems = checkItem(item, inspectedItems);
            numberOfInspections++;
        }

        return inspectedItems;
    }

    /**
     * @param item           value of the item
     * @param inspectedItems List of inspected items
     * @return List of Pairs containing the target monkey (left) and value f the item (right)
     */
    private List<Pair<Integer, Integer>> checkItem(
            Integer item, List<Pair<Integer, Integer>> inspectedItems) {
        var resultList = new ArrayList<>(List.copyOf(inspectedItems));
        var result = runOperation(item);
        var checkResult = checkResultValue(result);

        if (checkResult == true) {
            resultList.add(Pair.of(monkeyWhenTrue, item));
        } else {
            resultList.add(Pair.of(monkeyWhenFalse, item));
        }

        return resultList;
    }

    private int runOperation(Integer item) {
        if (modifier == -1) {
            return item * item;
        }

        if (this.operation == Operations.ADDITION) {
            return item + modifier;
        } else if (this.operation == Operations.MULTIPLICATION) {
            return item * modifier;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkResultValue(int result) {
        return result % this.divisor == 0;
    }

    public Monkey(int id, List<Integer> items, Operations operation, int modifier, int divisor, int monkeyWhenTrue, int monkeyWhenFalse) {
        this.id = id;
        this.items = items;
        this.operation = operation;
        this.modifier = modifier;
        this.divisor = divisor;
        this.monkeyWhenTrue = monkeyWhenTrue;
        this.monkeyWhenFalse = monkeyWhenFalse;
    }
}
