package com.poc.global.racecondition;

/* not thread safe */
public class GlobalCounter {

    private static Integer value = 0;

    public static int getValue() {
        return value.intValue();
    }

    public static void increment() {
        value = Integer.valueOf(value.intValue() + 1);
    }
}
