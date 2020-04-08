package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;
import chess.location.Row;
import chess.team.Team;

public class PawnPieceMovable implements PieceMovable {
    private static final Row WHITE_PAWN_ROW = Row.of(2);
    private static final Row BLACK_PAWN_ROW = Row.of(7);
    private static final int BLACK_PLUS_VALUE = -1;
    private static final int WHITE_PLUS_VALUE = 1;

    private final Team team;

    public PawnPieceMovable(Team team) {
        this.team = team;
    }

    @Override
    public boolean canMove(Route route) {
        int value = getValue();

        return canMoveDiagonal(route, value)
                || canMoveInInitialPawnLocation(route, value)
                || canMoveOnce(route, value);
    }

    private int getValue() {
        if (team.isBlack()) {
            return BLACK_PLUS_VALUE;
        }
        return WHITE_PLUS_VALUE;
    }

    private boolean canMoveDiagonal(Route route, int value) {
        return route.isForwardDiagonal(value) && isDestinationEmptyOrEnemy(route);
    }

    private boolean isDestinationEmptyOrEnemy(Route route) {
        return route.isEmptyDestinaion() || route.isEnemyNowAndDestination();
    }

    private boolean canMoveInInitialPawnLocation(Route route, int value) {
        return isInitialPawnLocation(route.getNow(), team)
                && isInitialPawnForwardRange(route, value)
                && hasNotObstacle(route);
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

    @Override
    public boolean hasNotObstacle(Route route) {
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location next = now.calculateNextLocation(destination, 1);

        boolean isMoveByTwiceRowCommand = Math.abs(now.getRowValue() - destination.getRowValue()) == 2;
        boolean hasObstacle = route.isExistPieceIn(next) || route.isExistPieceIn(destination);
        return isMoveByTwiceRowCommand && !hasObstacle;
    }

    private boolean canMoveOnce(Route route, int value) {
        return isPawnForwardOneLine(route, value) && hasNotObstacleWhenOneRowMove(route);
    }

    private boolean isPawnForwardOneLine(Route route, int value) {
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location movedRowByValue = now.plusRowBy(value);

        return movedRowByValue.isSameRow(destination)
                && movedRowByValue.isSameCol(destination);
    }

    private boolean hasNotObstacleWhenOneRowMove(Route route) {
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location next = now.calculateNextLocation(destination, 1);

        return route.isEmpty(next);
    }
}
