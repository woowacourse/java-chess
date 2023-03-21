package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import chess.domain.piece.state.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.chessboard.FileIndex.A;
import static chess.domain.chessboard.FileIndex.H;
import static chess.domain.chessboard.RankIndex.*;

public final class ChessFactory {

    private static final ChessFactory INSTANCE = new ChessFactory();

    private ChessFactory() {
    }

    public static ChessFactory getInstance() {
        return INSTANCE;
    }

    public Map<SquareCoordinate, PieceState> create() {
        Map<SquareCoordinate, PieceState> squares = new LinkedHashMap<>();

        createSideRankByTeam(squares, FIRST.index, Team.WHITE);
        createPawnRankByTeam(squares, SECOND.index, Team.WHITE);
        createBlankRanks(squares);
        createPawnRankByTeam(squares, SEVENTH.index, Team.BLACK);
        createSideRankByTeam(squares, EIGHTH.index, Team.BLACK);

        return squares;
    }

    private void createSideRankByTeam(Map<SquareCoordinate, PieceState> squares, final char rank, final Team team) {
        final List<PieceState> sideSquares = createSideSquaresByTeam(team);

        char file = A.index;
        for (PieceState pieceState : sideSquares) {
            squares.put(SquareCoordinate.of(makeAlphanumeric(file++, rank)), pieceState);
        }
    }

    private List<PieceState> createSideSquaresByTeam(final Team team) {
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

    private String makeAlphanumeric(final char file, final char rank) {
        return String.valueOf(file) + rank;
    }

    private void createPawnRankByTeam(Map<SquareCoordinate, PieceState> squares, final char rank, final Team team) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(SquareCoordinate.of(makeAlphanumeric(file, rank)), new Pawn(team));
        }
    }

    private void createBlankRanks(Map<SquareCoordinate, PieceState> squares) {
        for (char rank = THIRD.index; rank <= SIXTH.index; rank++) {
            createBlankRank(squares, rank);
        }
    }

    private void createBlankRank(Map<SquareCoordinate, PieceState> squares, final char rank) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(SquareCoordinate.of(makeAlphanumeric(file, rank)), new Empty());
        }
    }
}
