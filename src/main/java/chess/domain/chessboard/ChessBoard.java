package chess.domain.chessboard;

import static chess.domain.chessboard.FileIndex.A;
import static chess.domain.chessboard.FileIndex.H;
import static chess.domain.chessboard.RankIndex.EIGHTH;
import static chess.domain.chessboard.RankIndex.FIRST;
import static chess.domain.chessboard.RankIndex.SECOND;
import static chess.domain.chessboard.RankIndex.SEVENTH;
import static chess.domain.chessboard.RankIndex.SIXTH;
import static chess.domain.chessboard.RankIndex.THIRD;

import chess.domain.piece.Team;
import chess.domain.piece.state.Bishop;
import chess.domain.piece.state.King;
import chess.domain.piece.state.Knight;
import chess.domain.piece.state.Pawn;
import chess.domain.piece.state.Queen;
import chess.domain.piece.state.Rook;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {

    private final Map<Coordinate, Square> squares = new LinkedHashMap<>();
    private Team currentTurn = Team.WHITE;

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
            squares.put(Coordinate.of(makeAlphanumeric(file, rank)), new Square());
        }
    }

    public void move(final Coordinate from, final Coordinate to) {
        validateTurn(from);
        final Square departure = squares.get(from);
        final Square arrival = squares.get(to);
        validateCanMove(from, to, departure);

        arrival.switchPieceState(departure);
        departure.switchPieceState(new Square());
        changeTurn();
    }

    private void validateTurn(final Coordinate from) {
        if (checkNotCurrentTurn(from)) {
            throw new IllegalArgumentException("현재 턴은 " + currentTurn + "입니다!");
        }
    }

    private boolean checkNotCurrentTurn(final Coordinate from) {
        return !squares.get(from).isMyTeam(currentTurn);
    }

    private void changeTurn() {
        currentTurn = currentTurn.flip();
    }

    private void validateCanMove(final Coordinate from, final Coordinate to, final Square departure) {
        final List<Coordinate> route = departure.findRoute(from, to);

        final List<Square> routeSquares = route.stream()
                .map(squares::get)
                .collect(Collectors.toUnmodifiableList());

        departure.validateRoute(routeSquares);
    }

    public List<Square> getSquares() {
        return this.squares.values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
