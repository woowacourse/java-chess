package domain.coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<Integer, Position> CACHE = new HashMap<>();

    private final int value;

    static {
        for (int i = 0; i < 8; i++) {
            CACHE.put(i, new Position(i));
        }
    }

    private Position(int value) {
        this.value = value;
    }

    public static Position of(int value) {
        Position position = CACHE.get(value);

        if (position == null) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        return position;
    }

    public Position moveBy(int offset) {
        return Position.of(value + offset);
    }

    public int calculateDifference(Position other) {
        return other.value - value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position1 = (Position) o;
        return value == position1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
