package net.joshuabrandes.service;

import lombok.Getter;

@Getter
public class Counter {
    int count;

    public Counter() {
        count = 0;
    }

    public int incrementCount() {
        return ++count;
    }

}
