package net.joshuabrandes.data;

import net.joshuabrandes.model.Monkey;
import net.joshuabrandes.model.Operations;

import java.util.List;

public class DataProvider {
    public static List<Monkey> monkeyList = List.of(
            new Monkey(0, List.of(98, 89, 52), Operations.MULTIPLICATION, 2, 5, 6, 1),
            new Monkey(1, List.of(57, 95, 80, 92, 57, 78), Operations.MULTIPLICATION, 13, 2, 2, 6),
            new Monkey(2, List.of(82, 74, 97, 75, 51, 92, 83), Operations.ADDITION, 5, 19, 7, 5),
            new Monkey(3, List.of(97, 88, 51, 68, 76), Operations.ADDITION, 6, 7, 0, 4),
            new Monkey(4, List.of(63), Operations.ADDITION, 1, 17, 0, 1),
            new Monkey(5, List.of(94, 91, 51, 63), Operations.ADDITION, 4, 13, 4, 13),
            new Monkey(6, List.of(61, 54, 94, 71, 74, 68, 98, 83), Operations.ADDITION, 2, 3, 2, 7),
            new Monkey(7, List.of(90, 56), Operations.MULTIPLICATION, -1, 11, 3, 5)
    );
}
