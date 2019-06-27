package chess.util;

public class DoubleCompare {
    private static final double DELTA = 0.001;

    public static boolean deltaCompare(double num1, double num2) {
        return Math.abs(num1 - num2) < DELTA;
    }
}
