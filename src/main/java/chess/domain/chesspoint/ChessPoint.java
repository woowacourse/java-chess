package chess.domain.chesspoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessPoint {
    public static final int START = 1;
    public static final int END = 8;
    private static final String ERROR_OUT_OF_SCOPE = "범위를 벗어났습니다.";
    private static Map<String, ChessPoint> pointPool = new HashMap<>();
    private final ChessRow row;
    private final ChessColumn column;

    private ChessPoint(int row, int column) {
        if (isOutOfScope(row, column)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_SCOPE);
        }
        this.row = ChessRow.of(row);
        this.column = ChessColumn.of(column);
    }

    private boolean isOutOfScope(int row, int column) {
        return isOutOfRange(row) || isOutOfRange(column);
    }

    private boolean isOutOfRange(int number) {
        return number < START || number > END;
    }

    public static ChessPoint of(int row, int column) {
        String key = hashPoint(row, column);

        if (pointPool.containsKey(key)) {
            return pointPool.get(key);
        }

        ChessPoint newChessPoint = new ChessPoint(row, column);
        pointPool.put(key, newChessPoint);
        return newChessPoint;
    }

    private static String hashPoint(int row, int column) {
        return "" + row + column;
    }

    public RelativeChessPoint minus(ChessPoint source) {
        return RelativeChessPoint.of(row.minus(source.row), column.minus(source.column));
    }

    public ChessPoint moveBy(RelativeChessPoint relativePoint) {
        return ChessPoint.of(row.plus(relativePoint.getRelativeRow()), column.plus(relativePoint.getRelativeColumn()));
    }

    public int getRow() {
        return row.getRow();
    }

    public int getColumn() {
        return column.getColumn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ChessPoint that = (ChessPoint) o;
        return Objects.equals(row, that.row) &&
                Objects.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
