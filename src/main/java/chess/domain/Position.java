package chess.domain;

import chess.exception.InvalidPositionException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private static final int MAX_POSITION = 7;
    private static final int MIN_POSITION = 0;
    private static final Map<String, Position> positions = new HashMap<>();

    private final int column;
    private final int row;

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(int column, int row) {
        validateValues(column, row);
        String key = "" + column + row;
        return positions.computeIfAbsent(key, newKey -> new Position(column, row));
    }

    private static void validateValues(int column, int row) {
        if (isNotValid(column, row)) {
            throw new InvalidPositionException();
        }
    }

    private static boolean isNotValid(int column, int row) {
        return row < MIN_POSITION || MAX_POSITION < row || column < MIN_POSITION
            || MAX_POSITION < column;
    }

    public boolean invalidGo(PieceDirection movePieceDirection) {
        return isNotValid(movePieceDirection.column() + column, movePieceDirection.row() + row);
    }

    public Position go(PieceDirection movePieceDirection) {
        return Position.of(movePieceDirection.column() + column, movePieceDirection.row() + row);
    }

    public int column() {
        return column;
    }

    public String columnAndRow() {
        return "" + column + row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position.of(" + column + ", " + row + ')';
    }
}
