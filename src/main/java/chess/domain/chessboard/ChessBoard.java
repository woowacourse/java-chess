package chess.domain.chessboard;

import chess.domain.piece.Empty;
import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import chess.domain.piece.state.King;
import chess.domain.piece.state.Pawn;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoard {
    public static final int INITIAL_KING_COUNT = 2;
    public static final int CLOSING_KING_COUNT = 1;
    public static final int MIN_DOUBLE_PAWN_COUNT = 2;
    public static final int NO_COUNT = 0;
    public static final int FIRST_INDEX = 0;

    private final Map<SquareCoordinate, SquareState> squares;

    public ChessBoard(Map<SquareCoordinate, SquareState> squares) {
        this.squares = squares;
    }

    public boolean isDifferentTeam(final Team team, final SquareCoordinate from) {
        return !squares.get(from).isSameTeam(team);
    }

    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        final SquareState departure = squares.get(from);
        validateCanMove(from, to, departure);

        squares.replace(to, departure);
        squares.replace(from, new Empty());
    }

    private void validateCanMove(final SquareCoordinate from, final SquareCoordinate to, final SquareState departure) {
        final List<SquareCoordinate> route = departure.findRoute(from, to);

        final List<SquareState> routeSquares = route.stream()
                .map(squares::get)
                .collect(Collectors.toUnmodifiableList());

        departure.validateRoute(routeSquares);
    }

    public boolean isKingDead() {
        int kingCount = (int) this.squares.values()
                .stream()
                .filter(squareState -> squareState.getClass().equals(King.class))
                .count();

        return kingCount < INITIAL_KING_COUNT;
    }

    public List<SquareState> getPiecesOf(final Team team) {
        return this.squares.values()
                .stream()
                .filter(squareState -> squareState.isSameTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    public int countDoublePawnOf(final Team team) {
        final List<SquareCoordinate> pawnCoordinates = getPawnCoordinatesOf(team);

        int count = 0;
        for (FileIndex file : FileIndex.values()) {
            count += countDoublePawnEachFile(file, pawnCoordinates);
        }

        return count;
    }

    private List<SquareCoordinate> getPawnCoordinatesOf(final Team team) {
        return this.squares.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .filter(entry -> entry.getValue().getClass().equals(Pawn.class))
                .map(Map.Entry::getKey)
                .collect(Collectors.toUnmodifiableList());
    }

    private int countDoublePawnEachFile(final FileIndex file, final List<SquareCoordinate> pawnCoordinates) {
        final String alphanumeric = String.valueOf(file.index) + RankIndex.FIRST.index;

        final int curCount = SquareCoordinate.of(alphanumeric).countSameFiles(pawnCoordinates);
        if (curCount >= MIN_DOUBLE_PAWN_COUNT) {
            return curCount;
        }
        return NO_COUNT;
    }

    public Team findTeamHavingKing() {
        final List<SquareState> kingPiece = this.squares.values().stream()
                .filter(piece -> piece.getClass().equals(King.class))
                .collect(Collectors.toList());

        if (kingPiece.size() == CLOSING_KING_COUNT) {
            return kingPiece.get(FIRST_INDEX).getTeam();
        }
        throw new IllegalArgumentException("게임이 진행 중이므로 종료할 수 없습니다.");
    }

    public ChessBoard copyChessBoard(){
        return new ChessBoard(Collections.unmodifiableMap(this.squares));
    }

    public List<SquareState> getSquares() {
        return this.squares.values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
