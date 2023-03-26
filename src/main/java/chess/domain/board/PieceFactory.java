package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PieceFactory {

    public static Map<Position, Piece> createPiece() {
        Map<Position, Piece> result = createEmptyPiece();
        List<Team> teams = List.of(Team.BLACK, Team.WHITE);

        for (final Team team : teams) {
            createPawn(result, Rank.of(team, true), team);
            createRook(result, Rank.of(team, false), team);
            createKnight(result, Rank.of(team, false), team);
            createBishop(result, Rank.of(team, false), team);
            createQueen(result, Rank.of(team, false), team);
            createKing(result, Rank.of(team, false), team);
        }

        return result;
    }

    public static Map<Position, Piece> createEmptyPiece() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> Position.of(file, rank))
                ).collect(Collectors.toMap(
                        Function.identity(),
                        piece -> new EmptyPiece()
                ));
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
