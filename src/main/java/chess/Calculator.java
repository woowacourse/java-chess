package chess;

public class Calculator {
    public static int divideAbsoluteValue(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }
}
