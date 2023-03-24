package chess.domain.position;

import java.math.BigInteger;
import java.util.Objects;

public class RelativePosition {

    private final int x;
    private final int y;

    public RelativePosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static RelativePosition of(final Position source, final Position target) {
        int x = target.getColumn() - source.getColumn();
        int y = target.getRow() - source.getRow();
        return new RelativePosition(x, y);
    }

    public RelativePosition toUnit() {
        if (x == 0 && y == 0) {
            return new RelativePosition(0, 0);
        }
        if (x == 0) {
            return new RelativePosition(0, y / Math.abs(y));
        }
        if (y == 0) {
            return new RelativePosition(x / Math.abs(x), 0);
        }
        return toUnitNoneZeroPosition();
    }

    private RelativePosition toUnitNoneZeroPosition() {
        int gcd = getGreatestCommonDivisor(Math.abs(x), Math.abs(y));
        return new RelativePosition(x / gcd, y / gcd);
    }

    private int getGreatestCommonDivisor(final int x, final int y) {
        BigInteger bigX = BigInteger.valueOf(x);
        BigInteger bigY = BigInteger.valueOf(y);
        return bigX.gcd(bigY).intValue();
    }

    public RelativePosition inverseByXAxis() {
        return new RelativePosition(x, -1 * y);
    }

    public boolean isDiagonal() {
        return Math.abs(x) == Math.abs(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RelativePosition that = (RelativePosition) o;

        if (x != that.x)
            return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
