package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import chess.domain.piece.state.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.chessboard.FileIndex.A;
import static chess.domain.chessboard.FileIndex.H;
import static chess.domain.chessboard.RankIndex.*;

public final class ChessFactory {

    public static Map<SquareCoordinate, SquareState> create() {
        final Map<SquareCoordinate, SquareState> squares = new LinkedHashMap<>();

        createSideRankByTeam(squares, FIRST.index, Team.WHITE);
        createPawnRankByTeam(squares, SECOND.index, Team.WHITE);
        createBlankRanks(squares);
        createPawnRankByTeam(squares, SEVENTH.index, Team.BLACK);
        createSideRankByTeam(squares, EIGHTH.index, Team.BLACK);

        return squares;
    }

    private static void createSideRankByTeam(final Map<SquareCoordinate, SquareState> squares, final char rank, final Team team) {
        final List<SquareState> sideSquares = createSideSquaresByTeam(team);

        char file = A.index;
        for (SquareState squareState : sideSquares) {
            squares.put(SquareCoordinate.of(makeAlphanumeric(file++, rank)), squareState);
        }
    }

    private static List<SquareState> createSideSquaresByTeam(final Team team) {
        return List.of(
                new Rook(team),
                new Knight(team),
                new Bishop(team),
                new Queen(team),
                new King(team),
                new Bishop(team),
                new Knight(team),
                new Rook(team));
    }

    private static String makeAlphanumeric(final char file, final char rank) {
        return String.valueOf(file) + rank;
    }

    private static void createPawnRankByTeam(final Map<SquareCoordinate, SquareState> squares, final char rank, final Team team) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(SquareCoordinate.of(makeAlphanumeric(file, rank)), new Pawn(team));
        }
    }

    private static void createBlankRanks(final Map<SquareCoordinate, SquareState> squares) {
        for (char rank = THIRD.index; rank <= SIXTH.index; rank++) {
            createBlankRank(squares, rank);
        }
    }

    private static void createBlankRank(final Map<SquareCoordinate, SquareState> squares, final char rank) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(SquareCoordinate.of(makeAlphanumeric(file, rank)), new Empty());
        }
    }
}
