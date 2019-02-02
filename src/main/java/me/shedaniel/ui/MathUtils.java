package me.shedaniel.ui;

public class MathUtils {
    
    public static int clamp(int a, int min, int max) {
        if (a < min)
            return min;
        if (a > max)
            return max;
        return a;
    }
    
    public static int roll(int a, int min, int max, int addition) {
        while (a < min)
            a += addition;
        while (a > max)
            a -= addition;
        return a;
    }
    
}
