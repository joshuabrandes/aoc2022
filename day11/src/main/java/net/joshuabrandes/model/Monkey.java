package net.joshuabrandes.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Monkey {

    List<Integer> items;
    Operations operation;
    int modifier;
    int divisor;
    int monkeyWhenTrue;
    int monkeyWhenFalse;

    public Pair<Integer, List<Pair<Integer, Integer>>> inspectItems() {
        var itemAmount = items.size();
        List<Pair<Integer, Integer>> movedItems = new ArrayList<>();
        for (Integer item : items) {
            checkItem(item, movedItems);
        }

        return Pair.of(itemAmount, movedItems);
    }

    private List<Pair<Integer, Integer>> checkItem(
            Integer item, List<Pair<Integer, Integer>> movedItems) {
        var result = runOperation(item);
        var checkResult = checkResultValue(result);

    }

    private int runOperation(Integer item) {
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

}
