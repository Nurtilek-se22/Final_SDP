package utils;

public class MathUtils {
    public static <T extends Comparable<T>> T clamp(T val, T min, T max) {
        if (val.compareTo(min) < 0) return min;
        else if (val.compareTo(max) > 0) return max;
        else return val;
    }
    public static double clamp(double val, double min, double max) {
        return val < min ? min : val > max ? max : val;
    }
    public static int clamp(int val, int min, int max) {
        return val < min ? min : val > max ? max : val;
    }
    public static char clamp(char val, char min, char max) {
        return val < min ? min : val > max ? max : val;
    }
    public static byte clamp(byte val, byte min, byte max) {
        return val < min ? min : val > max ? max : val;
    }
}
