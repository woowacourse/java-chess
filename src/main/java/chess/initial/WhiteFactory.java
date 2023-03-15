package chess.initial;

import chess.File;
import chess.domain.Position;
import chess.domain.piece.*;

import java.util.Map;

public final class WhiteFactory {

    public static Map<Position, Piece> create(final Map<Position, Piece> board) {
        fillWhitePiece(board);
        return board;
    }

    private static void fillWhitePiece(final Map<Position, Piece> board) {
        fillWhiteRook(board);
        fillWhiteKnight(board);
        fillWhiteBishop(board);
        fillWhiteKing(board);
        fillWhiteQueen(board);
        fillWhitePawn(board);
    }

    private static void fillWhiteRook(final Map<Position, Piece> board) {
        board.put(Position.of('a', 1), new Rook("r"));
        board.put(Position.of('h', 1), new Rook("r"));
    }

    private static void fillWhiteKnight(final Map<Position, Piece> board) {
        board.put(Position.of('g', 1), new Knight("n"));
        board.put(Position.of('b', 1), new Knight("n"));
    }

    private static void fillWhiteBishop(final Map<Position, Piece> board) {
        board.put(Position.of('c', 1), new Bishop("b"));
        board.put(Position.of('f', 1), new Bishop("b"));
    }

    private static void fillWhiteQueen(final Map<Position, Piece> board) {
        board.put(Position.of('d', 1), new Queen("q"));
    }

    private static void fillWhiteKing(final Map<Position, Piece> board) {
        board.put(Position.of('e', 1), new King("k"));
    }

    private static void fillWhitePawn(final Map<Position, Piece> board) {
        for (final File file : File.values()) {
            board.put(Position.of(file.value(), 2), new Pawn("p"));
        }
    }
}
