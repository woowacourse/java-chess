package chess.domain.board;

import static java.util.stream.Collectors.toMap;

import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position implements Comparable<Position> {

    private final static Map<String, Position> CACHE;

    private final Column column;
    private final Row row;

    static {
        CACHE = createAll().stream()
                .collect(toMap(Position::generateRawPosition, position -> position));
    }

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position valueOf(final String rawPosition) {
        if (!CACHE.containsKey(rawPosition)) {
            throw new IllegalArgumentException("[ERROR] 좌표가 체스판의 범위를 초과하였습니다.");
        }
        return CACHE.get(rawPosition);
    }

    private String generateRawPosition() {
        return column.getName() + row.getValue();
    }

    private static List<Position> createAll() {
        List<Position> positions = new ArrayList<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                positions.add(new Position(column, row));
            }
        }
        return positions;
    }

    public int subtractColumn(final Position position) {
        return this.column.subtractValue(position.column);
    }

    public int subtractRow(final Position position) {
        return this.row.subtractValue(position.row);
    }

    public Position move(int horizon, int vertical) {
        String column = this.column.move(horizon).getName();
        int row = this.row.move(vertical).getValue();
        return Position.valueOf(column + row);
    }

    public boolean isPawnStartPosition(final Team team) {
        if (team == Team.BLACK && row == Row.SEVEN) {
            return true;
        }
        return team == Team.WHITE && row == Row.TWO;
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
