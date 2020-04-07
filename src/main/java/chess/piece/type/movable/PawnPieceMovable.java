package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;
import chess.location.Row;
import chess.team.Team;

public class PawnPieceMovable implements PieceMovable {
    private static final Row WHITE_PAWN_ROW = Row.of(2);
    private static final Row BLACK_PAWN_ROW = Row.of(7);

    private final Team team;

    public PawnPieceMovable(Team team) {
        this.team = team;
    }

    @Override
    public boolean canMove(Route route) {
        int value = 1;
        if (team.isBlack()) {
            value = -1;
        }
        return canMoveDiagonal(route, value)
                || canMoveInInitialPawnLocation(route, value)
                || canMoveOnce(route, value);
    }

    private boolean canMoveDiagonal(Route route, int value) {
        return route.isForwardDiagonal(value) && canMoveDiagonal(route);
    }

    private boolean canMoveDiagonal(Route route) {
        return route.isDiagonal() && (route.isEmptyDestinaion() || route.isEnemyNowAndDestination());
    }

    private boolean canMoveOnce(Route route, int value) {
        return isPawnForwardRange(route, value) && hasNotObstacle(route);
    }

    private boolean isPawnForwardRange(Route route, int value) {
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location movedRowByValue = now.plusRowBy(value);

        return movedRowByValue.isSameRow(destination)
                && movedRowByValue.isSameCol(destination);
    }

    private boolean canMoveInInitialPawnLocation(Route route, int value) {
        return isInitialPawnLocation(route.getNow(), team)
                && isInitialPawnForwardRange(route, value)
                && hasNotObstacle(route);
    }

    @Override
    public boolean hasNotObstacle(Route route) {
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location next = now.calculateNextLocation(destination, 1);
        boolean isMoveByOnceRowCommand = Math.abs(now.getRowValue() - destination.getRowValue()) == 1;
        if (isMoveByOnceRowCommand && route.isNotEmpty(next)) {
            return false;
        }

        boolean isMoveByTwiceRowCommand = Math.abs(now.getRowValue() - destination.getRowValue()) == 2;
        boolean hasObstacle = route.isNotEmpty(next) || route.isNotEmpty(destination);

        return isMoveByTwiceRowCommand || !hasObstacle;
    }

    private boolean isInitialPawnLocation(Location location, Team team) {
        if (team.isBlack()) {
            return location.isSameRow(BLACK_PAWN_ROW);
        }
        return location.isSameRow(WHITE_PAWN_ROW);
    }

    private boolean isInitialPawnForwardRange(Route route, int value) {
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location onceMovedRowByValue = now.plusRowBy(value);
        Location TwiceMovedRowByValue = now.plusRowBy(value * 2);

        boolean isOnceOrTwiceRowMoved =
                onceMovedRowByValue.isSameRow(destination) || TwiceMovedRowByValue.isSameRow(destination);

        return isOnceOrTwiceRowMoved && now.isSameCol(destination);
    }
}
