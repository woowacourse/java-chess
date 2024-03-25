package chess.domain;

public class Calculator {
    public static int calculateMinMovement(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }
}
