package chess.domain.piece;

import chess.domain.chessboard.SquareCoordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;

public abstract class Piece implements SquareState {

    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return this.team.equals(piece.team);
    }

    @Override
    public boolean isSameTeam(final Team team) {
        return this.team.equals(team);
    }

    @Override
    public Team getTeam() {
        return team;
    }

    protected final List<SquareCoordinate> horizontalRoute(final SquareCoordinate from, final SquareCoordinate to) {
        final int distance = from.calculateFileDistance(to);

        return makeRouteCoordinates(distance, from::horizontalMove);
    }

    protected final List<SquareCoordinate> verticalRoute(final SquareCoordinate from, final SquareCoordinate to) {
        final int distance = from.calculateRankDistance(to);

        return makeRouteCoordinates(distance, from::verticalMove);
    }

    protected final List<SquareCoordinate> positiveDiagonalRoute(final SquareCoordinate from, final SquareCoordinate to) {
        final int distance = from.calculateFileDistance(to);

        return makeRouteCoordinates(distance, from::positiveDiagonalMove);
    }

    protected final List<SquareCoordinate> negativeDiagonalRoute(final SquareCoordinate from, final SquareCoordinate to) {
        final int distance = from.calculateRankDistance(to);

        return makeRouteCoordinates(distance, from::negativeDiagonalMove);
    }

    private List<SquareCoordinate> makeRouteCoordinates(final int distance,
                                                        final IntFunction<SquareCoordinate> coordinateFunction) {
        final List<SquareCoordinate> route = new ArrayList<>();
        final int direction = directionByDistance(distance);
        for (int i = 1; i <= direction * distance; i++) {
            route.add(coordinateFunction.apply(direction * i));
        }
        return Collections.unmodifiableList(route);
    }

    private int directionByDistance(final int distance) {
        if (distance < 0) {
            return -1;
        }
        return 1;
    }

    @Override
    public void validateRoute(final List<SquareState> routeSquares) {
        final int lastIndex = routeSquares.size() - 1;
        final SquareState lastSquare = routeSquares.get(lastIndex);
        if (lastSquare.isSameTeam(this)) {
            throwCanNotMoveException();
        }

        checkSquaresEmpty(routeSquares.subList(0, lastIndex));
    }

    protected final void checkSquaresEmpty(final List<SquareState> squares) {
        final int notEmptyCount = (int) squares.stream()
                .filter(square -> !square.isEmpty())
                .count();

        if (notEmptyCount > 0) {
            throwCanNotMoveException();
        }
    }

    protected final void throwCanNotMoveException() {
        throw new IllegalArgumentException(this.getClass().getSimpleName() + "(은)는 해당 좌표로 이동할 수 없습니다.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
