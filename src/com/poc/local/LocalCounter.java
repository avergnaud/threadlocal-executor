package com.poc.local;

import java.util.HashMap;
import java.util.Map;

public class LocalCounter {

    private static ThreadLocal<Integer> counter = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };

    public static int getValue() {
        return counter.get();
    }

    public static void increment() {
        counter.set(Integer.valueOf(getValue() + 1));
        log.put(Thread.currentThread().getName(),counter.get());
    }

    // counter.remove() is never called

    // only for monitoring purpose:
    public static Map<String,Integer> log = new HashMap<>();
}
