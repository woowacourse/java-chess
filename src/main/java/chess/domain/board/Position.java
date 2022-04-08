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
                .collect(toMap(
                        position -> String.valueOf(position.column.getValue()) + position.row.getValue(),
                        position -> position
                ));
    }

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(final char columValue, final int rowValue) {
        String key = String.valueOf(columValue) + rowValue;
        validateExistPosition(key);
        return CACHE.get(key);
    }

    private static void validateExistPosition(String refinedRawPosition) {
        if (!CACHE.containsKey(refinedRawPosition)) {
            throw new IllegalArgumentException("[ERROR] 체스판에 존재하지 않는 위치 좌표 입니다.");
        }
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
        char columnValue = this.column.move(horizon).getValue();
        int rowValue = this.row.move(vertical).getValue();
        return Position.of(columnValue, rowValue);
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

    public Row getRow() {
        return row;
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
