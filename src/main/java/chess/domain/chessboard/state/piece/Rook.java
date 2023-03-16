package chess.domain.chessboard.state.piece;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.Team;
import java.util.ArrayList;
import java.util.List;

public final class Rook extends Piece {

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        validatePossibleDestination(from, to);

        if (from.isSameFile(to)) {
            return verticalRoute(from, to);
        }
        return horizontalRoute(from, to);
    }

    private List<Coordinate> horizontalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateFileDistance(to);

        if (distance < 0) {
            return horizontalRouteBySign(from, distance, -1);
        }

        return horizontalRouteBySign(from, distance, 1);
    }

    private List<Coordinate> horizontalRouteBySign(final Coordinate from, final int distance, int sign) {
        final List<Coordinate> route = new ArrayList<>();
        for (int i = 1; i <= sign * distance; i++) {
            route.add(from.horizontalMove(sign * i));
        }
        return route;
    }

    private List<Coordinate> verticalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateRankDistance(to);

        if (distance < 0) {
            return verticalRouteBySign(from, distance, -1);
        }

        return verticalRouteBySign(from, distance, 1);
    }

    private List<Coordinate> verticalRouteBySign(final Coordinate from, final int distance, int sign) {
        final List<Coordinate> route = new ArrayList<>();
        for (int i = 1; i <= sign * distance; i++) {
            route.add(from.verticalMove(sign * i));
        }
        return route;
    }

    private void validatePossibleDestination(final Coordinate from, final Coordinate to) {
        if (!(from.isSameFile(to) || from.isSameRank(to))) {
            throwCanNotMoveException();
        }
    }

    @Override
    public void canMove(final List<Square> routeSquares) {

        final int lastIndex = routeSquares.size() - 1;
        final Square lastSquare = routeSquares.get(lastIndex);
        if (lastSquare.isSameTeam(this)) {
            throwCanNotMoveException();
        }

        for (Square curSquare : routeSquares.subList(0, lastIndex)) {
            checkSquareEmpty(curSquare);
        }
    }

    private void checkSquareEmpty(final Square curSquare) {
        if (!curSquare.isEmpty()) {
            throwCanNotMoveException();
        }
    }
}
