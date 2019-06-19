package chess.domain.chesspoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessPoint {
    public static final int START = 1;
    public static final int END = 8;
    private static final String ERROR_OUT_OF_SCOPE = "범위를 벗어났습니다.";
    private static final int BASE_FOR_DECIMAL_NUMBER_IN_HASH_FUNC = 10;

    private static Map<Integer, ChessPoint> pointPool = new HashMap<>();
    private final int row;
    private final int column;

    private ChessPoint(int row, int column) {
        if (isOutOfScope(row, column)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_SCOPE);
        }
        this.row = row;
        this.column = column;
    }

    private boolean isOutOfScope(int row, int column) {
        return isOutOfRange(row) || isOutOfRange(column);
    }

    private boolean isOutOfRange(int number) {
        return number < START || number > END;
    }

    public static ChessPoint of(int row, int column) {
        int key = hashPoint(row, column);

        if (pointPool.containsKey(key)) {
            return pointPool.get(key);
        }

        ChessPoint chessPoint = new ChessPoint(row, column);
        pointPool.put(key, chessPoint);
        return chessPoint;
    }

    private static int hashPoint(int row, int column) {
        return makeDecimalNumber(row, column);
    }

    private static int makeDecimalNumber(int row, int column) {
        return row * BASE_FOR_DECIMAL_NUMBER_IN_HASH_FUNC + column;
    }

    public RelativeChessPoint minus(ChessPoint source) {
        return RelativeChessPoint.of(row - source.row, column - source.column);
    }

    public ChessPoint moveBy(RelativeChessPoint relativePoint) {
        return ChessPoint.of(row + relativePoint.getRelativeRow(), column + relativePoint.getRelativeColumn());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ChessPoint chessPoint = (ChessPoint) o;
        return row == chessPoint.row &&
                column == chessPoint.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
