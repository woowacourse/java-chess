package chess.domain.position;

import chess.exception.PositionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Position {
    private static final int POSITION_LENGTH = 2;
    private static final int POSITIVE_INCLINE = 1;
    private static final int NEGATIVE_INCLINE = -1;

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    // TODO 캐싱
    public static Position from(String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new PositionException("유효하지 않은 좌표입니다.");
        }

        return new Position(
                Column.from(position.charAt(0)),
                Row.from(position.charAt(1)));
    }

    public static int differenceOfRow(Position from, Position to) {
        return Row.differance(from.row, to.row);
    }

    public static int differenceOfColumn(Position from, Position to) {
        return Column.differance(from.column, to.column);
    }

    public static List<Position> route(Position from, Position to) {

        int differenceOfColumn = differenceOfColumn(from, to);
        int differenceOfRow = differenceOfRow(from, to);

        if (sameColumn(from, to)) {
            return verticalRoute(differenceOfRow, to);
        }

        if (sameRow(from, to)) {
            return horizontalRoute(differenceOfColumn, to);
        }

        if (positiveDiagonal(from, to)) {
            return positiveDiagonalRoute(differenceOfColumn, to);
        }

        if (negativeDiagonal(from, to)) {
            return negativeDiagonalRoute(differenceOfColumn, to);
        }

        return Collections.emptyList();
    }

    private static List<Position> negativeDiagonalRoute(int differenceOfColumn, Position newPosition) {
        List<Position> result = new ArrayList<>();
        int abs = Math.abs(differenceOfColumn);
        int next = differenceOfColumn / abs;

        for (int i = 0; i < abs - 1; i++) {
            newPosition = newPosition.getNextNegativeInclinationPosition(next);
            result.add(newPosition);
        }
        return result;
    }

    private static List<Position> positiveDiagonalRoute(int differenceOfColumn, Position newPosition) {
        List<Position> result = new ArrayList<>();
        int abs = Math.abs(differenceOfColumn);
        int next = differenceOfColumn / abs;

        for (int i = 0; i < abs - 1; i++) {
            newPosition = newPosition.getNextPositiveInclinationPosition(next);
            result.add(newPosition);
        }
        return result;
    }

    private static List<Position> horizontalRoute(int differenceOfColumn, Position newPosition) {
        List<Position> result = new ArrayList<>();
        int abs = Math.abs(differenceOfColumn);
        int next = differenceOfColumn / abs;

        for (int i = 0; i < abs - 1; i++) {
            newPosition = newPosition.getNextColumnPosition(next);
            result.add(newPosition);
        }
        return result;
    }

    private static List<Position> verticalRoute(int differenceOfRow, Position newPosition) {
        List<Position> result = new ArrayList<>();
        int abs = Math.abs(differenceOfRow);
        int next = differenceOfRow / abs;

        for (int i = 0; i < abs - 1; i++) {
            newPosition = newPosition.getNextRowPosition(next);
            result.add(newPosition);
        }
        return result;
    }

    private static boolean positiveDiagonal(Position from, Position to) {
        return differenceOfRow(from, to) / differenceOfColumn(from, to) == POSITIVE_INCLINE;
    }

    private static boolean negativeDiagonal(Position from, Position to) {
        return differenceOfRow(from, to) / differenceOfColumn(from, to) == NEGATIVE_INCLINE;
    }

    private static boolean sameRow(Position from, Position to) {
        return differenceOfRow(from, to) == 0;
    }

    private static boolean sameColumn(Position from, Position to) {
        return differenceOfColumn(from, to) == 0;
    }

    private Position getNextPositiveInclinationPosition(int next) {
        return new Position(column.nextColumn(next), row.nextRow(next));
    }

    private Position getNextNegativeInclinationPosition(int next) {
        return new Position(column.nextColumn(next), row.nextRow(NEGATIVE_INCLINE * next));
    }

    private Position getNextRowPosition(int next) {
        return new Position(column, row.nextRow(next));
    }

    private Position getNextColumnPosition(int next) {
        return new Position(column.nextColumn(next), row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }

        Position position = (Position) o;

        if (row != position.row) {
            return false;
        }
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row != null ? row.hashCode() : 0;
        result = 31 * result + (column != null ? column.hashCode() : 0);
        return result;
    }
}
