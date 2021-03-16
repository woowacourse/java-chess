package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final Map<String, Position> positionMap = new HashMap<>();

    static {
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8"};
        String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                positionMap.put(numbers[i] + alphabets[j], new Position(i, j));
            }
        }
    }

    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final String input) {
        return positionMap.get(input);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
