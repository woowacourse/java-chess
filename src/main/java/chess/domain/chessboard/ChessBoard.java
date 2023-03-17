package chess.domain.chessboard;

import static chess.domain.chessboard.FileIndex.*;
import static chess.domain.chessboard.RankIndex.*;

import chess.domain.chessboard.state.Team;
import chess.domain.chessboard.state.piece.Bishop;
import chess.domain.chessboard.state.piece.King;
import chess.domain.chessboard.state.piece.Knight;
import chess.domain.chessboard.state.piece.Pawn;
import chess.domain.chessboard.state.piece.Queen;
import chess.domain.chessboard.state.piece.Rook;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private final Map<Coordinate, Square> squares = new LinkedHashMap<>();

    public ChessBoard() {
        initSquares();
    }

    private void initSquares() {
        createSideRankByTeam(FIRST.index, Team.WHITE);
        createPawnRankByTeam(SECOND.index, Team.WHITE);
        createBlankRanks();
        createPawnRankByTeam(SEVENTH.index, Team.BLACK);
        createSideRankByTeam(EIGHTH.index, Team.BLACK);
    }

    private void createSideRankByTeam(final char rank, final Team team) {
        final List<Square> sideSquares = createSideSquaresByTeam(team);

        char file = A.index;
        for (Square square : sideSquares) {
            this.squares.put(Coordinate.of(makeAlphanumeric(file++, rank)), square);
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

    private void createPawnRankByTeam(final char rank, final Team team) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(Coordinate.of(makeAlphanumeric(file, rank)), createPawnSquareByTeam(team));
        }
    }

    private Square createPawnSquareByTeam(final Team team) {
        return new Square(new Pawn(team));
    }

    private void createBlankRanks() {
        for (char rank = THIRD.index; rank <= SIXTH.index; rank++) {
            createBlankRank(rank);
        }
    }

    private void createBlankRank(final char rank) {
        for (char file = A.index; file <= H.index; file++) {
            squares.put(Coordinate.of(makeAlphanumeric(file, rank)), Square.emptySquare());
        }
    }

    public void move(final Coordinate from, final Coordinate to) {
        final Square departure = squares.get(from);
        final Square arrival = squares.get(to);
        validateCanMove(from, to, departure);

        arrival.switchPieceState(departure);
        departure.switchPieceState(Square.emptySquare());
    }

    private void validateCanMove(final Coordinate from, final Coordinate to, final Square departure) {
        final List<Coordinate> route = departure.findRoute(from, to);

        final List<Square> routeSquares = route.stream()
                .map(squares::get)
                .collect(Collectors.toUnmodifiableList());

        departure.validateRoute(routeSquares);
    }

    public List<Square> getSquares() {
        return this.squares.keySet()
                .stream()
                .map(squares::get)
                .collect(Collectors.toUnmodifiableList());
    }
}
