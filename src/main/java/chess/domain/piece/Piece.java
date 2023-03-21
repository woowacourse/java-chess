package chess.domain.piece;

import chess.domain.chessboard.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public abstract class Piece implements PieceState {

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
    public Team getTeam() {
        return team;
    }

    protected final List<Coordinate> horizontalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateFileDistance(to);

        return makeRouteCoordinates(distance, from::horizontalMove);
    }

    protected final List<Coordinate> verticalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateRankDistance(to);

        return makeRouteCoordinates(distance, from::verticalMove);
    }

    protected final List<Coordinate> positiveDiagonalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateFileDistance(to);

        return makeRouteCoordinates(distance, from::positiveDiagonalMove);
    }

    protected final List<Coordinate> negativeDiagonalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateRankDistance(to);

        return makeRouteCoordinates(distance, from::negativeDiagonalMove);
    }

    private List<Coordinate> makeRouteCoordinates(final int distance,
                                                  final IntFunction<Coordinate> coordinateFunction) {
        final List<Coordinate> route = new ArrayList<>();
        final int direction = directionByDistance(distance);
        for (int i = 1; i <= direction * distance; i++) {
            route.add(coordinateFunction.apply(direction * i));
        }
        return route;
    }

    private int directionByDistance(final int distance) {
        if (distance < 0) {
            return -1;
        }
        return 1;
    }

    @Override
    public void validateRoute(final List<PieceState> routeSquares) {
        final int lastIndex = routeSquares.size() - 1;
        final PieceState lastSquare = routeSquares.get(lastIndex);
        if (lastSquare.isSameTeam(this)) {
            throwCanNotMoveException();
        }

        checkSquaresEmpty(routeSquares.subList(0, lastIndex));
    }

    protected final void checkSquaresEmpty(final List<PieceState> squares) {
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
}
