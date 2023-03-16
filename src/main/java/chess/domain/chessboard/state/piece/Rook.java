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

    private static List<Coordinate> horizontalRoute(final Coordinate from, final Coordinate to) {
        final List<Coordinate> route = new ArrayList<>();
        final int distance = from.calculateFileDistance(to);

        for (int i = 1; i <= distance; i++) {
            route.add(from.horizontalMove(i));
        }
        return route;
    }

    private static List<Coordinate> verticalRoute(final Coordinate from, final Coordinate to) {
        final List<Coordinate> route = new ArrayList<>();
        final int distance = from.calculateRankDistance(to);

        for (int i = 1; i <= distance; i++) {
            route.add(from.verticalMove(i));
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
        if(lastSquare.isSameTeam(this)){
            throwCanNotMoveException();
        }

        for(Square curSquare : routeSquares.subList(0, lastIndex)){
            checkSquareEmpty(curSquare);
        }
    }

    private void checkSquareEmpty(final Square curSquare) {
        if(!curSquare.isEmpty()){
            throwCanNotMoveException();
        }
    }
}
