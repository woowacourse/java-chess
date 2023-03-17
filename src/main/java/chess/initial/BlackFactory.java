package chess.initial;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.team.Team;

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
        board.put(Position.from("h8"), new Rook(Team.BLACK));
        board.put(Position.from("a8"), new Rook(Team.BLACK));
    }

    private static void fillBlackKnight(final Map<Position, Piece> board) {
        board.put(Position.from("b8"), new Knight(Team.BLACK));
        board.put(Position.from("g8"), new Knight(Team.BLACK));
    }

    private static void fillBlackBishop(final Map<Position, Piece> board) {
        board.put(Position.from("c8"), new Bishop(Team.BLACK));
        board.put(Position.from("f8"), new Bishop(Team.BLACK));
    }

    private static void fillBlackQueen(final Map<Position, Piece> board) {
        board.put(Position.from("d8"), new Queen(Team.BLACK));
    }

    private static void fillBlackKing(final Map<Position, Piece> board) {
        board.put(Position.from("e8"), new King(Team.BLACK));
    }

    private static void fillBlackPawn(final Map<Position, Piece> board) {
        for (final File file : File.values()) {
            board.put(Position.of(file.value(), 7), new Pawn(Team.BLACK));
        }
    }
}
