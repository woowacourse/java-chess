package chess.domain.chesspoint;

import java.util.HashMap;
import java.util.Map;

public class ChessRow {
    private static Map<Integer, ChessRow> rowPool = new HashMap<>();
    private final int row;

    private ChessRow(int row) {
        this.row = row;
    }

    public static ChessRow of(int row) {
        if (rowPool.containsKey(row)) {
            return rowPool.get(row);
        }

        ChessRow newChessRow = new ChessRow(row);
        rowPool.put(row, newChessRow);
        return newChessRow;
    }

    int minus(ChessRow chessRow) {
        return this.row - chessRow.row;
    }

    int plus(int number) {
        return this.row + number;
    }

    int divide(int number) {
        return row / number;
    }

    boolean isZero() {
        return row == 0;
    }

    int getRow() {
        return row;
    }
}
