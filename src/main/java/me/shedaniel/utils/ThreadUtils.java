package me.shedaniel.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {
    
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);
    
    public static void run(Runnable runnable) {
        EXECUTOR_SERVICE.submit(runnable);
    }
    
}
