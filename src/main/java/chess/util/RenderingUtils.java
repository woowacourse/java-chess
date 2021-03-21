package chess.util;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RenderingUtils {

    private RenderingUtils() {
    }

    public static String renderBoard(Board board) {
        final String visualBoard = Stream.of(Row.values())
            .map(index -> renderRow(board, index) + " " + index.getNumber())
            .collect(Collectors.joining("\n"))
            ;
        final String columnInfo = "\nabcdefgh\n";
        return columnInfo + visualBoard + columnInfo;
    }

    private static String renderRow(Board board, Row row) {
        return Stream.of(Column.values())
            .map(column -> Position.ofColumnAndRow(column, row))
            .map(position -> renderPosition(board.findPieceBy(position)))
            .collect(Collectors.joining())
            ;
    }

    private static String renderPosition(Piece piece) {
        return piece.getName();
    }
}
