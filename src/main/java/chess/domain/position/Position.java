package chess.domain.position;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(String value) {
        Column column = Column.of(value.substring(0, 1));
        Row row = Row.of(value.substring(1));
        return new Position(column, row);
    }

    public static Map<Row, List<Position>> groupByRank(List<Position> pawnPositions) {
        return pawnPositions.stream()
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
