package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceFactory {

    private Map<Position, Piece> result;

    public static Map<Position, Piece> createPiece() {
        Map<Position, Piece> result = new HashMap<>();
        initPosition(result);

        List<Team> teams = List.of(Team.BLACK, Team.WHITE);

        for (final Team team : teams) {
            createPawn(result, Rank.from(team, true), team);
            createRook(result, Rank.from(team, false), team);
            createKnight(result, Rank.from(team, false), team);
            createBishop(result, Rank.from(team, false), team);
            createQueen(result, Rank.from(team, false), team);
            createKing(result, Rank.from(team, false), team);
        }

        return result;
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
