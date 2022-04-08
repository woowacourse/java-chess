package chess.domain.board;

import static java.util.stream.Collectors.toMap;

import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {

    private final static Map<String, Position> CACHE;

    static {
        CACHE = createAll().stream()
                .collect(toMap(Position::convertPositionToString, position -> position));
    }

    private final Column column;
    private final Row row;

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    private static List<Position> createAll() {
        return Arrays.stream(Row.values())
                .flatMap(row ->
                        Arrays.stream(Column.values())
                                .map(column -> new Position(column, row))
                )
                .collect(Collectors.toList());
    }

    public static Position valueOf(final String rawPosition) {
        if (!CACHE.containsKey(rawPosition)) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 Position 입력입니다.");
        }
        return CACHE.get(rawPosition);
    }

    public static Position valueOf(final Column column, final Row row) {
        return valueOf(column.getName() + row.getValue());
    }

    public int subtractColumn(final Position position) {
        return this.column.subtract(position.column);
    }

    public int subtractRow(final Position position) {
        return this.row.subtract(position.row);
    }

    public boolean isPawnStartPosition(final Team team) {
        if (team == Team.BLACK && row == Row.SEVEN) {
            return true;
        }
        return team == Team.WHITE && row == Row.TWO;
    }

    public Position move(final int horizon, final int vertical) {
        return Position.valueOf(this.column.move(horizon), this.row.move(vertical));
    }

    public String convertPositionToString() {
        return column.getName() + row.getValue();
    }

    public Position compareSmaller(Position position) {
        if (compareTo(position) > 0) {
            return position;
        }
        return this;
    }

    public Column getColumn() {
        return column;
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
    public int compareTo(Position position) {
        if (this.row.getValue() == position.row.getValue()) {
            return this.column.getValue() - position.column.getValue();
        }
        return position.row.getValue() - this.row.getValue();
    }
}
