package com.poc.local;

import java.util.HashMap;
import java.util.Map;

/* causes a memory leak ? */
public class LocalContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Hello world";
        }
    };
}
