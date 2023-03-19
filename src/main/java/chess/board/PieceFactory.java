package chess.board;

import chess.piece.Bishop;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

import java.util.List;
import java.util.Map;

public class PieceFactory {

    public static void createPiece(final Map<Position, Piece> piecePosition) {
        initPosition(piecePosition);
        List<Team> teams = List.of(Team.BLACK, Team.WHITE);
        for (final Team team : teams) {
            createPawn(piecePosition, Rank.from(team, true), team);
            createRook(piecePosition, Rank.from(team, false), team);
            createKnight(piecePosition, Rank.from(team, false), team);
            createBishop(piecePosition, Rank.from(team, false), team);
            createQueen(piecePosition, Rank.from(team, false), team);
            createKing(piecePosition, Rank.from(team, false), team);
        }
    }

    public static void createEmptyPiece(final Map<Position, Piece> piecePosition) {
        initPosition(piecePosition);
    }

    private static void initPosition(final Map<Position, Piece> piecePosition) {
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                piecePosition.put(Position.of(file, rank), new EmptyPiece());
            }
        }
    }

    private static void createPawn(final Map<Position, Piece> piecePosition, final Rank rank, final Team team) {
        for (final File file : File.values()) {
            piecePosition.put(Position.of(file, rank), new Pawn(team));
        }
    }

    private static void createRook(final Map<Position, Piece> piecePosition, final Rank rank, final Team team) {
        piecePosition.put(Position.of(File.A, rank), new Rook(team));
        piecePosition.put(Position.of(File.H, rank), new Rook(team));
    }

    private static void createKnight(final Map<Position, Piece> piecePosition, final Rank rank, final Team team) {
        piecePosition.put(Position.of(File.B, rank), new Knight(team));
        piecePosition.put(Position.of(File.G, rank), new Knight(team));
    }

    private static void createBishop(final Map<Position, Piece> piecePosition, final Rank rank, final Team team) {
        piecePosition.put(Position.of(File.C, rank), new Bishop(team));
        piecePosition.put(Position.of(File.F, rank), new Bishop(team));
    }

    private static void createQueen(final Map<Position, Piece> piecePosition, final Rank rank, final Team team) {
        piecePosition.put(Position.of(File.D, rank), new Queen(team));
    }

    private static void createKing(final Map<Position, Piece> piecePosition, final Rank rank, final Team team) {
        piecePosition.put(Position.of(File.E, rank), new King(team));
    }
}
