package chess.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import chess.piece.Bishop;
import chess.piece.BlackPawn;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;
import chess.piece.WhitePawn;

public class PieceFactory {

    public static Map<Position, Piece> createPiece() {
        final Map<Position, Piece> piecePosition = new HashMap<>();
        initPosition(piecePosition);
        for (final Team team : Team.values()) {
            createPawn(piecePosition, team);
            createRook(piecePosition, team);
            createKnight(piecePosition, team);
            createBishop(piecePosition, team);
            createQueen(piecePosition, team);
            createKing(piecePosition, team);
        }
        return piecePosition;
    }

    private static void initPosition(final Map<Position, Piece> piecePosition) {
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                piecePosition.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    private static void createPawn(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            Arrays.stream(File.values())
                    .forEach(file -> piecePosition.put(new Position(file, Rank.TWO), new WhitePawn()));
        }
        if (team == Team.BLACK) {
            Arrays.stream(File.values())
                    .forEach(file -> piecePosition.put(new Position(file, Rank.SEVEN), new BlackPawn()));
        }
    }

    private static void createRook(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.A, Rank.ONE), new Rook(team));
            piecePosition.put(new Position(File.H, Rank.ONE), new Rook(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.A, Rank.EIGHT), new Rook(team));
            piecePosition.put(new Position(File.H, Rank.EIGHT), new Rook(team));
        }

    }

    private static void createKnight(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.B, Rank.ONE), new Knight(team));
            piecePosition.put(new Position(File.G, Rank.ONE), new Knight(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.B, Rank.EIGHT), new Knight(team));
            piecePosition.put(new Position(File.G, Rank.EIGHT), new Knight(team));
        }

    }

    private static void createBishop(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.C, Rank.ONE), new Bishop(team));
            piecePosition.put(new Position(File.F, Rank.ONE), new Bishop(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.C, Rank.EIGHT), new Bishop(team));
            piecePosition.put(new Position(File.F, Rank.EIGHT), new Bishop(team));
        }
    }

    private static void createQueen(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.D, Rank.ONE), new Queen(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.D, Rank.EIGHT), new Queen(team));
        }
    }

    private static void createKing(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.E, Rank.ONE), new King(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.E, Rank.EIGHT), new King(team));
        }
    }
}
