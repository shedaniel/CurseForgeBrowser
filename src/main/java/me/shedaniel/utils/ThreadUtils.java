package me.shedaniel.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {
    
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(5);
    private static final ExecutorService IMAGE_LOADER_SERVICE = Executors.newScheduledThreadPool(5);
    
    public static void run(Runnable runnable) {
        EXECUTOR_SERVICE.submit(runnable);
    }
    
    public static void runImageLoad(Runnable runnable) {
        IMAGE_LOADER_SERVICE.submit(runnable);
    }
    
}
