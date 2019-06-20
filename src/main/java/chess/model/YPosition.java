package chess.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class YPosition {
    private static final int MIN_Y = 1;
    private static final int MAX_Y = 8;
    private static Map<Integer, YPosition> yPositions = new HashMap<>();
    private final int yPosition;

    static {
        for (int i = MIN_Y; i <= MAX_Y; i++) {
            yPositions.put(i, new YPosition(i));
        }
    }

    private YPosition(final int yPosition) {
        this.yPosition = yPosition;
    }

    public static YPosition valueOf(final int yPosition) {
        if (yPosition < MIN_Y || yPosition > MAX_Y) {
            throw new IllegalYPositionException(String.format("X 좌표는 %d이상 %d이하여야 합니다.", MIN_Y, MAX_Y));
        }
        return yPositions.get(yPosition);
    }

    public int calculateYsDiff(final YPosition target) {
        return Math.abs(this.yPosition - target.yPosition);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final YPosition yPosition1 = (YPosition) o;
        return yPosition == yPosition1.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yPosition);
    }

}
