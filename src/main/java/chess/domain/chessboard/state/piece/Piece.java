package chess.domain.chessboard.state.piece;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.PieceState;
import chess.domain.chessboard.state.Team;
import java.util.ArrayList;
import java.util.List;

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
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean isSameTeam(final Piece piece) {
        return this.team.equals(piece.team);
    }


    protected List<Coordinate> horizontalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateFileDistance(to);

        if (distance < 0) {
            return horizontalRouteBySign(from, distance, -1);
        }

        return horizontalRouteBySign(from, distance, 1);
    }

    protected List<Coordinate> horizontalRouteBySign(final Coordinate from, final int distance, int sign) {
        final List<Coordinate> route = new ArrayList<>();
        for (int i = 1; i <= sign * distance; i++) {
            route.add(from.horizontalMove(sign * i));
        }
        return route;
    }


    protected List<Coordinate> negativeDiagonalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateRankDistance(to);

        if (distance < 0) {
            return negativeDiagonalRouteBySign(from, distance, -1);
        }

        return negativeDiagonalRouteBySign(from, distance, 1);
    }

    protected List<Coordinate> negativeDiagonalRouteBySign(final Coordinate from, final int distance, final int sign) {
        final List<Coordinate> route = new ArrayList<>();
        for (int i = 1; i <= sign * distance; i++) {
            route.add(from.negativeDiagonalMove(sign * i));
        }
        return route;
    }

    protected List<Coordinate> verticalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateRankDistance(to);

        if (distance < 0) {
            return verticalRouteBySign(from, distance, -1);
        }

        return verticalRouteBySign(from, distance, 1);
    }

    protected List<Coordinate> verticalRouteBySign(final Coordinate from, final int distance, int sign) {
        final List<Coordinate> route = new ArrayList<>();
        for (int i = 1; i <= sign * distance; i++) {
            route.add(from.verticalMove(sign * i));
        }
        return route;
    }

    protected List<Coordinate> positiveDiagonalRoute(final Coordinate from, final Coordinate to) {
        final int distance = from.calculateFileDistance(to);

        if (distance < 0) {
            return positiveDiagonalRouteBySign(from, distance, -1);
        }

        return positiveDiagonalRouteBySign(from, distance, 1);
    }

    protected List<Coordinate> positiveDiagonalRouteBySign(final Coordinate from, final int distance, final int sign) {
        final List<Coordinate> route = new ArrayList<>();
        for (int i = 1; i <= sign * distance; i++) {
            route.add(from.positiveDiagonalMove(sign * i));
        }
        return route;
    }

    protected void throwCanNotMoveException() {
        throw new IllegalArgumentException(this.getClass().getSimpleName() + "(은)는 해당 좌표로 이동할 수 없습니다.");
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

    protected void checkSquareEmpty(final Square curSquare) {
        if (!curSquare.isEmpty()) {
            throwCanNotMoveException();
        }
    }
}
