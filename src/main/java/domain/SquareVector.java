package domain;

public record SquareVector(int x, int y) {

    public static SquareVector of(final Square source, final Square target) {
        return new SquareVector(target.subtractFile(source), target.subtractRank(source));
    }

    public SquareVector scaleDown() {
        final int gcd = findAbsGCD();

        if (gcd == 0) {
            return new SquareVector(0, 0);
        }
        return new SquareVector(x / gcd, y / gcd);
    }

    private int findAbsGCD() {
        int num1 = Math.abs(x);
        int num2 = Math.abs(y);

        while (num2 != 0) {
            final int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    public int divide(final SquareVector other) {
        if (this.x * other.y != this.y * other.x) {
            throw new IllegalArgumentException();
        }
        if (this.x == 0) {
            return this.y / other.y;
        }
        if (this.y == 0) {
            return this.x / other.x;
        }
        return 1;
    }

    public boolean isDiagonal() {
        return Math.abs(x) == Math.abs(y);
    }

    public boolean isHorizontalOrVertical() {
        return x == 0 || y == 0;
    }

    public boolean isManhattanDistance(final int distance) {
        return Math.abs(x) <= 1 && Math.abs(y) <= 1;
    }
}
