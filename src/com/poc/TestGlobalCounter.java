package com.poc;

import com.poc.global.SafeGlobalCounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestGlobalCounter {

    public static void main() throws InterruptedException {

        Runnable command = SafeGlobalCounter::increment;

        ExecutorService executor = null;
        try {
            executor = Executors.newFixedThreadPool(50);
            for(int i=0; i<10_000; i++) {
                executor.execute(command);
            }
        } finally {
            executor.shutdown();
        }

        executor.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println(SafeGlobalCounter.getValue());
    }
}
