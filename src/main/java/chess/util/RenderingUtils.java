package chess.util;

import chess.domain.Board;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.domain.piece.Piece;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RenderingUtils {

    private RenderingUtils() {
    }

    public static String renderBoard(Board board) {
        return Stream.of(Row.values())
                .map(index -> renderRow(board, index))
                .collect(Collectors.joining("\n"))
                ;
    }

    private static String renderRow(Board board, Row row) {
        return Stream.of(Column.values())
                .map(column -> Position.of(column, row))
                .map(position -> renderPosition(board.findPieceBy(position)))
                .collect(Collectors.joining())
                ;
    }

    private static String renderPosition(Piece piece) {
        return piece.getName();
    }
}
