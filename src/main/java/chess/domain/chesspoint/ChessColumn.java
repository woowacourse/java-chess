package chess.domain.chesspoint;

import java.util.HashMap;
import java.util.Map;

public class ChessColumn {
    private static Map<Integer, ChessColumn> columnPool = new HashMap<>();
    private final int column;

    private ChessColumn(int column) {
        this.column = column;
    }

    public static ChessColumn of(int column) {
        if (columnPool.containsKey(column)) {
            return columnPool.get(column);
        }

        ChessColumn newChessColumn = new ChessColumn(column);
        columnPool.put(column, newChessColumn);
        return newChessColumn;
    }

    int minus(ChessColumn chessColumn) {
        return this.column - chessColumn.column;
    }

    int plus(int number) {
        return this.column + number;
    }

    boolean isZero() {
        return column == 0;
    }

    int divide(int number) {
        return column / number;
    }

    int getColumn() {
        return column;
    }
}
