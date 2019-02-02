package me.shedaniel.ui;

public class MathUtils {
    
    public static int clamp(int a, int min, int max) {
        if (a < min)
            return min;
        if (a > max)
            return max;
        return a;
    }
    
}
