package chess.domain.board;

import chess.domain.piece.Direction;

import java.util.*;

public class Position implements Comparable<Position> {

    private static final Map<String, Position> positions;

    static {
        positions = new HashMap<>();
        for (Column column : Column.values()) {
            createPosition(column);
        }
    }

    private static void createPosition(Column column) {
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

    public Position nextPositionOf(Direction direction) {
        Column columnDestination = direction.findColumnDestination(column);
        Row rowDestination = direction.findRowDestination(row);

        return of(columnDestination, rowDestination);
    }

    public List<Position> pathTo(Direction direction, int count) {
        List<Position> path = new ArrayList<>();
        try {
            findPath(direction, count, path);
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return path;
    }

    private void findPath(Direction direction, int count, List<Position> path) {
        Position nextPosition = this;
        for (int i = 0; i < count; i++) {
            nextPosition = nextPosition.nextPositionOf(direction);
            path.add(nextPosition);
        }
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public int compareTo(Position position) {
        if (row.compareTo(position.row) == 0) {
            return column.compareTo(position.column);
        }
        return -row.compareTo(position.row);
    }
}
