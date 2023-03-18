package chess.util;

public class Mathematics {

    public static long getGCD(long num1, long num2) {
        if (num1 == 0 || num2 == 0) {
            return Math.max(num1, num2);
        }
        if (num1 < num2) {
            return getGCD(num2, num1);
        }
        if (num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1 % num2);
    }
}
