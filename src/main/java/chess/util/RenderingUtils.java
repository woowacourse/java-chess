package chess.util;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RenderingUtils {

    private static final String BLANK = " ";
    private static final String COLUMN_INFO = "\n  a b c d e f g h\n";

    private RenderingUtils() {
    }

    public static String renderBoard(Board board) {
        final String visualBoard = Stream.of(Row.values())
            .map(index -> index.getNumber() + BLANK + renderRow(board, index) + index.getNumber())
            .collect(Collectors.joining(System.lineSeparator()));
        return COLUMN_INFO + visualBoard + COLUMN_INFO;
    }

    private static String renderRow(Board board, Row row) {
        return Stream.of(Column.values())
            .map(column -> Position.of(column, row))
            .map(position -> renderPosition(board.findPieceBy(position)))
            .collect(Collectors.joining())
            ;
    }

    private static String renderPosition(Piece piece) {
        return piece.getName() + BLANK;
    }
}
