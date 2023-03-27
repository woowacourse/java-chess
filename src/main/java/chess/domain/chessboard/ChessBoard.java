package chess.domain.chessboard;

import static chess.domain.chessboard.FileIndex.A;
import static chess.domain.chessboard.FileIndex.H;
import static chess.domain.chessboard.RankIndex.EIGHTH;
import static chess.domain.chessboard.RankIndex.FIRST;
import static chess.domain.chessboard.RankIndex.SECOND;
import static chess.domain.chessboard.RankIndex.SEVENTH;
import static chess.domain.chessboard.RankIndex.SIXTH;
import static chess.domain.chessboard.RankIndex.THIRD;

import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.piece.state.Bishop;
import chess.domain.piece.state.King;
import chess.domain.piece.state.Knight;
import chess.domain.piece.state.Pawn;
import chess.domain.piece.state.Queen;
import chess.domain.piece.state.Rook;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
            this.squares.put(Coordinate.from(makeAlphanumeric(file++, rank)), square);
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
            squares.put(Coordinate.from(makeAlphanumeric(file, rank)), createPawnSquareByTeam(team));
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
            squares.put(Coordinate.from(makeAlphanumeric(file, rank)), new Square());
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

    public List<List<Square>> getSameFileSquaresByTeam(final Team team) {
        return squaresGroupByFile(squaresGroupByTeam(team));
    }

    private Map<Coordinate, Square> squaresGroupByTeam(final Team team) {
        return squares.entrySet()
                .stream()
                .filter(e -> e.getValue().isMyTeam(team))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private List<List<Square>> squaresGroupByFile(Map<Coordinate, Square> squares) {
        final List<List<Square>> sameFileSquares = new ArrayList<>();
        for (FileIndex fileIndex : FileIndex.files()) {
            sameFileSquares.add(squares.entrySet()
                    .stream()
                    .filter(e -> e.getKey().isMyFile(fileIndex))
                    .map(Entry::getValue)
                    .collect(Collectors.toUnmodifiableList()));
        }
        return sameFileSquares;
    }

    public List<Square> squaresByTeam(final Team team) {
        return squares.values()
                .stream()
                .filter(square -> square.isMyTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    public long countFileOfContinuousPawnByTeam(final Team team) {
        long sum = 0;

        for (FileIndex fileIndex : FileIndex.files()) {
            sum += countContinuousPawnInFile(fileIndex, team);
        }

        return sum;
    }

    private long countContinuousPawnInFile(FileIndex fileIndex, Team team) {
        long count = squares.entrySet().stream()
                .filter(entry -> entry.getKey().isMyFile(fileIndex))
                .filter(entry -> entry.getValue().isMyTeam(team))
                .filter(entry -> entry.getValue().isTypeOf(PieceType.PAWN))
                .count();
        if (count > 1) {
            return 1;
        }
        return 0;
    }
}
