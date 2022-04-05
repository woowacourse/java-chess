package chess.domain.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

public final class Position {

    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        return of(column.getName() + row.getName());
    }

    public static Position of(String value) {
        if (POSITIONS.containsKey(value)) {
            return POSITIONS.get(value);
        }

        Column column = Column.of(value.substring(0, 1));
        Row row = Row.of(value.substring(1));
        Position position = new Position(column, row);
        POSITIONS.put(value, position);
        return position;
    }

    public static Map<Row, List<Position>> groupByRow(List<Position> positions) {
        return positions.stream()
                .collect(groupingBy(position -> position.row));
    }

    public boolean isSameRow(Row row) {
        return this.row == row;
    }

    public int calculateColumnDifferenceTo(Position position) {
        return column.displacementTo(position.column);
    }

    public int calculateRowDifferenceTo(Position position) {
        return row.displacementTo(position.row);
    }

    public int calculateMaxLinearLengthTo(Position position) {
        return Math.max(Math.abs(calculateRowDifferenceTo(position)), Math.abs(calculateColumnDifferenceTo(position)));
    }

    public int calculateXSlope(Position target, int routeLength) {
        return calculateColumnDifferenceTo(target) / routeLength;
    }

    public int calculateYSlope(Position target, int routeLength) {
        return calculateRowDifferenceTo(target) / routeLength;
    }

    public Position displacedOf(int xDisplacement, int yDisplacement) {
        return new Position(column.displacedOf(xDisplacement), row.displacedOf(yDisplacement));
    }

    public String getName() {
        return column.getName() + row.getName();
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
}
