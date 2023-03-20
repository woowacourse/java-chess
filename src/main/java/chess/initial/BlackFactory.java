package chess.initial;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
        board.put(Position.of(File.H, Rank.EIGHT), new Rook(Team.BLACK));
        board.put(Position.of(File.A, Rank.EIGHT), new Rook(Team.BLACK));
    }

    private static void fillBlackKnight(final Map<Position, Piece> board) {
        board.put(Position.of(File.B, Rank.EIGHT), new Knight(Team.BLACK));
        board.put(Position.of(File.G, Rank.EIGHT), new Knight(Team.BLACK));
    }

    private static void fillBlackBishop(final Map<Position, Piece> board) {
        board.put(Position.of(File.C, Rank.EIGHT), new Bishop(Team.BLACK));
        board.put(Position.of(File.F, Rank.EIGHT), new Bishop(Team.BLACK));
    }

    private static void fillBlackQueen(final Map<Position, Piece> board) {
        board.put(Position.of(File.D, Rank.EIGHT), new Queen(Team.BLACK));
    }

    private static void fillBlackKing(final Map<Position, Piece> board) {
        board.put(Position.of(File.E, Rank.EIGHT), new King(Team.BLACK));
    }

    private static void fillBlackPawn(final Map<Position, Piece> board) {
        for (final File file : File.values()) {
            board.put(Position.of(file, Rank.SEVEN), new Pawn(Team.BLACK));
        }
    }
}
