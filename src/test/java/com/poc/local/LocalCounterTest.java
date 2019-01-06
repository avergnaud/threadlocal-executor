package com.poc.local;

import com.poc.global.SafeGlobalCounter;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LocalCounterTest {

    static final int ITERATIONS = 10_000;

    @Test
    public void test() throws InterruptedException {
        Runnable command = LocalCounter::increment;

        ExecutorService executor = null;
        try {
            executor = Executors.newFixedThreadPool(10);
            for(int i=0; i<10_000; i++) {
                executor.execute(command);
            }
        } finally {
            executor.shutdown();
        }

        executor.awaitTermination(2, TimeUnit.SECONDS);
        int total = 0;
        for (Map.Entry<String, Integer> entry : LocalCounter.log.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            total += entry.getValue();
        }
        System.out.println(total);
        assertEquals(ITERATIONS, total);
    }

}