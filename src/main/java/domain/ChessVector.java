package domain;

public record ChessVector(int x, int y) {

    public ChessVector scaleDown() {
        final int gcd = findAbsGCD();

        if (gcd == 0) {
            return new ChessVector(0, 0);
        }
        return new ChessVector(x / gcd, y / gcd);
    }

    public int findAbsGCD() {
        int num1 = Math.abs(x);
        int num2 = Math.abs(y);

        while (num2 != 0) {
            final int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    public boolean isDiagonal() {
        return Math.abs(x) == Math.abs(y);
    }

    public boolean isHorizontalOrVertical() {
        return x == 0 || y == 0;
    }

    public boolean isManhattanDistance(final int distance) {
        return Math.abs(x) <= distance && Math.abs(y) <= distance;
    }
}
