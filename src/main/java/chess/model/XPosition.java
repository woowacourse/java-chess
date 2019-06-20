package chess.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class XPosition {
    private static final int MIN_X = 1;
    private static final int MAX_X = 8;
    private static Map<Integer, XPosition> xPositions = new HashMap<>();
    private final int xPosition;

    static {
        for (int i = MIN_X; i <= MAX_X; i++) {
            xPositions.put(i, new XPosition(i));
        }
    }

    private XPosition(final int xPosition) {
        this.xPosition = xPosition;
    }

    public static XPosition valueOf(final int xPos) {
        if (xPos < MIN_X || xPos > MAX_X) {
            throw new IllegalXPositionException(String.format("X 좌표는 %d이상 %d이하여야 합니다.", MIN_X, MAX_X));
        }
        return xPositions.get(xPos);
    }

    public int calculateXsDiff(final XPosition target) {
        return Math.abs(this.xPosition - target.xPosition);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final XPosition xPosition1 = (XPosition) o;
        return xPosition == xPosition1.xPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition);
    }

}