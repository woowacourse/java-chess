package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private static final String ERROR_INVALID_PATTERN = "문자 1개 숫자 1개를 붙인 위치형식으로 입력해 주세요.";
    private static final String PATTERN = "^[a-z][0-9]$";
    private static final char ROW_LEFT_BOUND = 'a';
    private static final char COLUMN_UPPER_BOUND = '8';

    private final Row row;
    private final Column column;

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position from(String position) {
        validatePattern(position);
        Row row = Row.from(parseRow(position));
        Column column = Column.from(parseColumn(position));
        return new Position(row, column);
    }

    private static void validatePattern(String position) {
        if (!position.matches(PATTERN)) {
            throw new IllegalArgumentException(ERROR_INVALID_PATTERN);
        }
    }

    private static int parseRow(String position) {
        return position.charAt(0) - ROW_LEFT_BOUND;
    }

    private static int parseColumn(String position) {
        return COLUMN_UPPER_BOUND - position.charAt(1);
    }

    public List<Position> generatePath(Position target) {
        List<Position> path = new ArrayList<>();
        int vectorRow = row.vectorTo(target.row);
        int vectorColumn = column.vectorTo(target.column);

        Position current = this;
        do {
            current = current.add(vectorRow, vectorColumn);
            path.add(current);
        } while (!current.equals(target));

        return path;
    }

    private Position add(int vectorRow, int vectorColumn) {
        return new Position(row.add(vectorRow), column.add(vectorColumn));
    }

    public boolean isSameRow(Position other) {
        return row == other.row;
    }

    public boolean isSameRow(Row otherRow) {
        return row == otherRow;
    }

    public boolean isSameColumn(Position other) {
        return column == other.column;
    }

    public boolean isSameColumn(Column otherColumn) {
        return column == otherColumn;
    }

    public boolean isSameDiagonal(Position other) {
        return distanceRowFrom(other) == distanceColumnFrom(other);
    }

    public int distanceRowFrom(Position other) {
        return row.distance(other.row);
    }

    public int distanceColumnFrom(Position other) {
        return column.distance(other.column);
    }

    public boolean isUpperThan(Position other) {
        return row.compareTo(other.row) < 0;
    }

    public boolean isLowerThan(Position other) {
        return row.compareTo(other.row) > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position other)) return false;
        return row == other.row && column == other.column;
    }

    public int getRow() {
        return row.ordinal();
    }

    public int getColumn() {
        return column.ordinal();
    }
}
