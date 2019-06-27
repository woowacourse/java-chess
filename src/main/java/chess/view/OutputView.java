package chess.view;

import chess.model.Column;
import chess.model.Row;
import chess.model.Square;
import chess.model.board.Board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputView {
    private static final String NEW_LINE = "\n";

    public static String board(Board board) {
        StringBuilder sb = new StringBuilder();
        List<Column> columns = Arrays.asList(Column.values());
        Collections.reverse(columns);
        for (Column column : columns) {
            for (Row row : Row.values()) {
                Square square = Square.of(column, row);
                sb.append(board.isNullPiece(square) ? "." : board.getPiece(square).toSymbolString());
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }
}