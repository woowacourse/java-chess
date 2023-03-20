package chess.initial;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
        board.put(Position.of(File.A, Rank.ONE), new Rook(Team.WHITE));
        board.put(Position.of(File.H, Rank.ONE), new Rook(Team.WHITE));
    }

    private static void fillWhiteKnight(final Map<Position, Piece> board) {
        board.put(Position.of(File.G, Rank.ONE), new Knight(Team.WHITE));
        board.put(Position.of(File.B, Rank.ONE), new Knight(Team.WHITE));
    }

    private static void fillWhiteBishop(final Map<Position, Piece> board) {
        board.put(Position.of(File.C, Rank.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(File.F, Rank.ONE), new Bishop(Team.WHITE));
    }

    private static void fillWhiteQueen(final Map<Position, Piece> board) {
        board.put(Position.of(File.D, Rank.ONE), new Queen(Team.WHITE));
    }

    private static void fillWhiteKing(final Map<Position, Piece> board) {
        board.put(Position.of(File.E, Rank.ONE), new King(Team.WHITE));
    }

    private static void fillWhitePawn(final Map<Position, Piece> board) {
        for (final File file : File.values()) {
            board.put(Position.of(file, Rank.TWO), new Pawn(Team.WHITE));
        }
    }
}
