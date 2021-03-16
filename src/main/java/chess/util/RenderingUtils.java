package chess.util;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RenderingUtils {

    public static String renderBoard(Board board) {
        return IntStream.rangeClosed(1,8)
            .mapToObj(index -> renderColumn(board, 8 - index + 1))
            .collect(Collectors.joining("\n"));
    }

    private static String renderColumn(Board board, int column) {
        return IntStream.rangeClosed(1,8)
            .mapToObj(row -> Position.of(row, column))
            .map(position -> renderPosition(board, position))
            .collect(Collectors.joining());
    }

    private static String renderPosition(Board board, Position position) {
        return board.findPieceBy(position)
            .map(Piece::getName)
            .orElse(".");
    }
}
