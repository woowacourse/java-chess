package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final Column col;
    private final Row row;

    public Position(Column col, Row row) {
        this.col = col;
        this.row = row;
    }

    public static Position from(String position) throws IllegalArgumentException {
        Column col = Column.find(position.charAt(0));
        Row row = Row.find(Character.getNumericValue(position.charAt(1)));
        return new Position(col, row);
    }

    public Column getCol() {
        return col;
    }

    public Row getRow() {
        return row;
    }

    public List<Position> getPathToDst(Position destination, Direction direction) {
        List<Position> positions = new ArrayList<>();
        addPositions(destination, direction, positions);
        return positions;
    }

    private void addPositions(Position destination, Direction direction, List<Position> positions) {
        char colValue = (char) (col.getValue() + direction.getXDegree());
        int rowValue = row.getValue() + direction.getYDegree();
        while (!(colValue == destination.getCol().getValue() && rowValue == destination.getRow().getValue())) {
            positions.add(new Position(Column.find(colValue), Row.find(rowValue)));
            colValue += direction.getXDegree();
            rowValue += direction.getYDegree();
        }
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
