package chess.utils;

public class UnitCalculator {
    public static int getUnit(int difference) {
        return difference / Math.abs(difference);
    }
}
