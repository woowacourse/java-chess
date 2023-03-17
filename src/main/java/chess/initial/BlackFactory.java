package chess.initial;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.piece.*;

import java.util.Map;

public final class BlackFactory {

    public static Map<Position, Piece> create(final Map<Position, Piece> board) {
        fillBlackPiece(board);
        return board;
    }

    private static void fillBlackPiece(final Map<Position, Piece> board) {
        fillBlackRook(board);
        fillBlackKnight(board);
        fillBlackBishop(board);
        fillBlackKing(board);
        fillBlackQueen(board);
        fillBlackPawn(board);
    }

    private static void fillBlackRook(final Map<Position, Piece> board) {
        board.put(Position.of('a', 8), new Rook("R"));
        board.put(Position.of('h', 8), new Rook("R"));
    }

    private static void fillBlackKnight(final Map<Position, Piece> board) {
        board.put(Position.of('b', 8), new Knight("N"));
        board.put(Position.of('g', 8), new Knight("N"));
    }

    private static void fillBlackBishop(final Map<Position, Piece> board) {
        board.put(Position.of('c', 8), new Bishop("B"));
        board.put(Position.of('f', 8), new Bishop("B"));
    }

    private static void fillBlackQueen(final Map<Position, Piece> board) {
        board.put(Position.of('d', 8), new Queen("Q"));
    }

    private static void fillBlackKing(final Map<Position, Piece> board) {
        board.put(Position.of('e', 8), new King("K"));
    }

    private static void fillBlackPawn(final Map<Position, Piece> board) {
        for (final File file : File.values()) {
            board.put(Position.of(file.value(), 7), new Pawn("P"));
        }
    }
}
