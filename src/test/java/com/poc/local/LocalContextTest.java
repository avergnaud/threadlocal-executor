package com.poc.local;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* causes a memory leak ? */
public class LocalContextTest {

    static AtomicLong counter = new AtomicLong(0);

    @Test
    public void test() {
        // no thread pool. we just keep on creating threads
        Runnable command = () -> {
            /* adds a string to the jre String pool */
            LocalContext.threadLocal.set(Thread.currentThread().getName() + " value");
            counter.incrementAndGet();
        };

        // watch thread creation count
        Runnable info = () -> System.out.println(counter.incrementAndGet());

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(info, 0, 3, TimeUnit.SECONDS);

        while(true) {
            Thread thread = new Thread(command);
            thread.start();
            // thread should be garbage collected
            // no memory leak. ThreadLocal uses an internal hash table, ans "the hash table entries use WeakReferences for keys"
        }
    }

}