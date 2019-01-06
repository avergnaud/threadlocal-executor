package com.poc;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("start?");
        System.in.read();

        //TestGlobalCounter.main();
        TestLocalCounter.main();
    }
}
