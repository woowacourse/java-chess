package chess.domain.chessboard;

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

    public Map<Coordinate, Square> create() {
        Map<Coordinate, Square> squares = new LinkedHashMap<>();

        createSideRankByTeam(squares, FIRST.index, Team.WHITE);
        createPawnRankByTeam(squares, SECOND.index, Team.WHITE);
        createBlankRanks(squares);
        createPawnRankByTeam(squares, SEVENTH.index, Team.BLACK);
        createSideRankByTeam(squares, EIGHTH.index, Team.BLACK);

        return squares;
    }

    private void createSideRankByTeam(Map<Coordinate, Square> squares, final char rank, final Team team) {
        final List<Square> sideSquares = createSideSquaresByTeam(team);

        char file = A.index;
        for (Square square : sideSquares) {
            squares.put(Coordinate.of(makeAlphanumeric(file++, rank)), square);
        }
    }

    private List<Square> createSideSquaresByTeam(final Team team) {
        return List.of(
                new Square(new Rook(team)),
                new Square(new Knight(team)),
                new Square(new Bishop(team)),
                new Square(new Queen(team)),
                new Square(new King(team)),
                new Square(new Bishop(team)),
                new Square(new Knight(team)),
                new Square(new Rook(team)));
    }

    private String makeAlphanumeric(final char file, final char rank) {
        return String.valueOf(file) + rank;
    }

    private void createPawnRankByTeam(Map<Coordinate, Square> squares, final char rank, final Team team) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(Coordinate.of(makeAlphanumeric(file, rank)), createPawnSquareByTeam(team));
        }
    }

    private Square createPawnSquareByTeam(final Team team) {
        return new Square(new Pawn(team));
    }

    private void createBlankRanks(Map<Coordinate, Square> squares) {
        for (char rank = THIRD.index; rank <= SIXTH.index; rank++) {
            createBlankRank(squares, rank);
        }
    }

    private void createBlankRank(Map<Coordinate, Square> squares, final char rank) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(Coordinate.of(makeAlphanumeric(file, rank)), new Square());
        }
    }
}
