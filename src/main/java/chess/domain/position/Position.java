package chess.domain.position;

import chess.domain.util.Direction;
import chess.exception.InvalidPositionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    public static final int START_INDEX = 1;
    public static final int END_INDEX = 8;
    private static final int ASCII_GAP = 96;
    private static final Map<String, Position> ALL_POSITIONS = new HashMap<>();

    private final int col;
    private final int row;

    static {
        for (int row = START_INDEX; row <= END_INDEX; row++) {
            for (int col = START_INDEX; col <= END_INDEX; col++) {
                ALL_POSITIONS.put(convertToStringPosition(col, row), new Position(col, row));
            }
        }
    }

    private Position(final int col, final int row) {
        this.col = col;
        this.row = row;
    }

    public static Position of(final String position) {
        return ALL_POSITIONS.keySet().stream()
                .filter(key -> key.equals(position))
                .map(ALL_POSITIONS::get)
                .findFirst()
                .orElseThrow(() -> new InvalidPositionException(position));
    }

    public static Position of(final int col, final int row) {
        return of(convertToStringPosition(col, row));
    }

    private static String convertToStringPosition(final int col, final int row) {
        return (char) (col + ASCII_GAP) + String.valueOf(row);
    }

    public Position moveBy(final Direction direction) {
        return Position.of(convertToStringPosition(col + direction.getColumn(), row + direction.getRow()));
    }

    public boolean isNextPositionValidForward(final Direction direction) {
        int nextCol = col + direction.getColumn();
        int nextRow = row + direction.getRow();
        return START_INDEX <= nextCol && nextCol <= END_INDEX
                && START_INDEX <= nextRow && nextRow <= END_INDEX;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return col == position.col &&
                row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return convertToStringPosition(col, row);
    }
}
