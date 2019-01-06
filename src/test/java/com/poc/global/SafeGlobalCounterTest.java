package com.poc.global;

import com.poc.global.racecondition.GlobalCounter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SafeGlobalCounterTest {

    static final int ITERATIONS = 10_000;

    @Test
    public void test() throws InterruptedException {

        Runnable command = SafeGlobalCounter::increment;

        ExecutorService executor = null;
        try {
            executor = Executors.newFixedThreadPool(50);
            for(int i=0; i<ITERATIONS; i++) {
                executor.execute(command);
            }
        } finally {
            executor.shutdown();
        }

        executor.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println(SafeGlobalCounter.getValue());
        /* equals because there is no more race condition */
        assertEquals(ITERATIONS, SafeGlobalCounter.getValue());
    }

}