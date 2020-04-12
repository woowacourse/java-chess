package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import chess.domain.piece.Direction;

public class Position implements Comparable<Position> {

    private static final Map<String, Position> positions;

    static {
        positions = new HashMap<>();
        for (Column column : Column.values()) {
            createPositions(column);
        }
    }

    private static void createPositions(final Column column) {
        for (Row row : Row.values()) {
            String name = nameOf(column, row);
            positions.put(name, new Position(column, row));
        }
    }

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        return from(nameOf(column, row));
    }

    public static Position from(String name) {
        Position position = positions.get(name.toUpperCase());
        validate(name, position);
        return position;
    }

    private static void validate(String name, Position position) {
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException(String.format("%s는 잘못된 입력입니다.", name));
        }
    }

    private static String nameOf(Column column, Row row) {
        return column.getName() + row.getName();
    }

    public static List<Position> list() {
        return Collections.unmodifiableList(new ArrayList<>(positions.values()));
    }

    public Position opposite() {
        return of(column.opposite(), row.opposite());
    }

    public Position horizontalFlip() {
        return of(column, row.opposite());
    }

    public Optional<Position> nextPositionOf(Direction direction) {
        Optional<Column> columnDestination = direction.findColumnDestination(column);
        Optional<Row> rowDestination = direction.findRowDestination(row);

        if (!columnDestination.isPresent() || !rowDestination.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(of(columnDestination.get(), rowDestination.get()));
    }

    public List<Position> pathTo(Direction direction, int count) {
        List<Position> path = new ArrayList<>();
        Position nextPosition = this;

        for (int i = 0; i < count && nextPosition.nextPositionOf(direction).isPresent(); i++) {
            nextPosition = nextPosition.nextPositionOf(direction).get();
            path.add(nextPosition);
        }
        return path;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    public String getName() {
        return column.getName() + row.getName();
    }

    @Override
    public int compareTo(Position position) {
        if (row.compareTo(position.row) == 0) {
            return column.compareTo(position.column);
        }
        return -row.compareTo(position.row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Position))
            return false;
        Position position = (Position)o;
        return column == position.column &&
                row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
