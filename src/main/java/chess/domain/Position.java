package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toMap;

public class Position {
    private final static Map<String, Position> positions;
    private final static int positionToStringLength = 2;

    private final Column col;
    private final Row row;

    static {
        positions = createPositions().stream()
                .collect(toMap(Position::getPositionToString, position -> position));
    }

    private static List<Position> createPositions() {
        List<Position> positions = new ArrayList<>();
        for (Row row : Row.values()) {
            createPosition(positions, row);
        }
        return positions;
    }

    private static void createPosition(List<Position> positions, Row row) {
        for (Column col : Column.values()) {
            positions.add(new Position(col, row));
        }
    }

    private static String getPositionToString(Position position) {
        return position.getCol().getSymbol() + position.getRow().getSymbol();
    }

    public static Position of(Column col, Row row) throws RuntimeException{
        String position = col.getSymbol() + row.getSymbol();
        return positions.get(position);
    }

    public static Position from(String position) throws RuntimeException{
        validateLength(position);
        return positions.get(position);
    }

    private static void validateLength(String position) {
        if (position.length() != positionToStringLength) {
            throw new IllegalArgumentException("올바른 포지션 값이 아닙니다.");
        }
    }

    private Position(Column col, Row row) {
        this.col = col;
        this.row = row;
    }

    public Column getCol() {
        return col;
    }

    public Row getRow() {
        return row;
    }

    public int getRowDifference(Row row) {
        return this.row.getDifference(row);
    }

    public int getColDifference(Column col) {
        return this.col.getDifference(col);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return col == position.col && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
