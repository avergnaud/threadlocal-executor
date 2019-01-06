package com.poc.global;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeGlobalCounter {

    private static AtomicInteger value = new AtomicInteger(0);

    public static int getValue() {
        return value.intValue();
    }

    public static void increment() {
        value.incrementAndGet();
    }
}
