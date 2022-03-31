package chess.domain.position;

import chess.domain.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position {

    public static final int COLUMN_INDEX = 0;
    public static final int ROW_INDEX = 1;

    public static final Map<String, Position> POSITION_CACHE = new HashMap<>();

    private final Column col;
    private final Row row;

    private Position(Column col, Row row) {
        this.col = col;
        this.row = row;
    }

    public static Position from(String position) throws IllegalArgumentException {
        if (!POSITION_CACHE.containsKey(position)) {
            Column col = Column.find(position.charAt(COLUMN_INDEX));
            Row row = Row.find(Character.getNumericValue(position.charAt(ROW_INDEX)));
            POSITION_CACHE.put(position, new Position(col, row));
        }
        return POSITION_CACHE.get(position);
    }

    public Column getCol() {
        return col;
    }

    public Row getRow() {
        return row;
    }

    public int getColDifference(Position destination) {
        return col.getDifference(destination.col);
    }

    public int getRowDifference(Position destination) {
        return row.getDifference(destination.row);
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
            positions.add(Position.from(colValue + String.valueOf(rowValue)));
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
