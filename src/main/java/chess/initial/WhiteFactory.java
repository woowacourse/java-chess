package chess.initial;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.team.Team;

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
        board.put(Position.from("a1"), new Rook(Team.WHITE));
        board.put(Position.from("h1"), new Rook(Team.WHITE));
    }

    private static void fillWhiteKnight(final Map<Position, Piece> board) {
        board.put(Position.from("g1"), new Knight(Team.WHITE));
        board.put(Position.from("b1"), new Knight(Team.WHITE));
    }

    private static void fillWhiteBishop(final Map<Position, Piece> board) {
        board.put(Position.from("c1"), new Bishop(Team.WHITE));
        board.put(Position.from("f1"), new Bishop(Team.WHITE));
    }

    private static void fillWhiteQueen(final Map<Position, Piece> board) {
        board.put(Position.from("d1"), new Queen(Team.WHITE));
    }

    private static void fillWhiteKing(final Map<Position, Piece> board) {
        board.put(Position.from("e1"), new King(Team.WHITE));
    }

    private static void fillWhitePawn(final Map<Position, Piece> board) {
        for (final File file : File.values()) {
            board.put(Position.of(file.value(), 2), new Pawn(Team.WHITE));
        }
    }
}
