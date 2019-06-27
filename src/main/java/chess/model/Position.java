package chess.model;

import chess.model.exception.IllegalPositionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final int MIN_POSITION = 1;
    private static final int MAX_POSITION = 8;
    private static Map<Integer, Position> positions = new HashMap<>();

    static {
        for (int i = MIN_POSITION; i <= MAX_POSITION; i++) {
            positions.put(i, new Position(i));
        }
    }

    private final int position;

    private Position(final int position) {
        this.position = position;
    }

    public static Position valueOf(final int position) {
        if (position < MIN_POSITION || position > MAX_POSITION) {
            throw new IllegalPositionException(String.format("좌표는 %d이상 %d이하여야 합니다.", MIN_POSITION, MAX_POSITION));
        }
        return positions.get(position);
    }

    public int calculateDiff(final Position target) {
        return Math.abs(this.position - target.position);
    }


    public int calculateSub(final Position another) {
        return position - another.position;
    }

    public Position add(final int delta) throws IllegalPositionException {
        return Position.valueOf(position + delta);
    }

    public int getValue() {
        return position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
