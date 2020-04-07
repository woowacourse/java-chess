package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;
import chess.location.Row;
import chess.piece.type.Piece;
import chess.team.Team;

import java.util.Map;

import static spark.route.HttpMethod.after;

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
//        System.out.println("canMoveDiagonal(route, value)" + canMoveDiagonal(route, value) );
//        System.out.println("canMoveInInitialPawnLocation(route, value)" + canMoveInInitialPawnLocation(route, value));
//        System.out.println("canMoveDiagonal(route, value)" + canMoveDiagonal(route, value) );
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

//    @Override
//    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
//        int value = 1;
//        if (team.isBlack()) {
//            value = -1;
//        }
//        return canMoveDiagonal(board, now, after, value)
//                || canMoveInInitialPawnLocation(board, now, after, value)
//                || canMoveOnce(board, now, after, value);
//    }

    private boolean canMoveOnce(Route route, int value) {
        return isPawnForwardRange(route, value) && hasNotObstacle(route);
//        return isPawnForwardRange(now, after, value) && hasNotObstacle(board, now, after);
    }

//    private boolean canMoveOnce(Map<Location, Piece> board, Location now, Location after, int value) {
//        return isPawnForwardRange(now, after, value) && hasNotObstacle(board, now, after);
//    }

    private boolean isPawnForwardRange(Route route, int value) {
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location movedRowByValue = now.plusRowBy(value);

        return movedRowByValue.isSameRow(destination)
                && movedRowByValue.isSameCol(destination);
    }

//    private boolean isPawnForwardRange(Location now, Location after, int value) {
//        Location movedRowByValue = now.plusRowBy(value);
//
//        return movedRowByValue.isSameRow(after)
//                && movedRowByValue.isSameCol(after);
//    }

//    private boolean canMoveDiagonal(Map<Location, Piece> board, Location now, Location after, int value) {
//        return now.isForwardDiagonal(after, value) && canMoveDiagonal(board, now, after);
//    }

    private boolean canMoveInInitialPawnLocation(Route route, int value) {
        return isInitialPawnLocation(route.getNow(), team)
                && isInitialPawnForwardRange(route, value)
                && hasNotObstacle(route);
//        return isInitialPawnLocation(now, team)
//                && isInitialPawnForwardRange(now, after, value)
//                && hasNotObstacle(board, now, after);
    }

//    private boolean canMoveInInitialPawnLocation(Map<Location, Piece> board, Location now, Location after, int value) {
//        return isInitialPawnLocation(now, team)
//                && isInitialPawnForwardRange(now, after, value)
//                && hasNotObstacle(board, now, after);
//    }

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

//    @Override
//    public boolean hasNotObstacle(Map<Location, Piece> board, Location now, Location after) {
//        Location nextLocation = now.calculateNextLocation(after, 1);
//        boolean isMoveByOnceRowCommand = Math.abs(now.getRowValue() - after.getRowValue()) == 1;
//        if (isMoveByOnceRowCommand && board.containsKey(nextLocation)) {
//            return false;
//        }
//        boolean isMoveByTwiceRowCommand = Math.abs(now.getRowValue() - after.getRowValue()) == 2;
//        boolean hasNotObstacle = board.containsKey(nextLocation) || board.containsKey(after);
//
//        return !isMoveByTwiceRowCommand || !hasNotObstacle;
//    }

//    private boolean canMoveDiagonal(Map<Location, Piece> board, Location now, Location after) {
//        if (isDiagonalAndIsReverseTeam(board, now, after)) {
//            Piece maybeEnemyPiece = board.get(after);
//            return maybeEnemyPiece.isReverseTeam(team);
//        }
//        return now.isDiagonal(after);
//    }

//    private boolean isDiagonalAndIsReverseTeam(Map<Location, Piece> board, Location now, Location after) {
//        return now.isDiagonal(after) && board.containsKey(after);
//    }

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

//    private boolean isInitialPawnForwardRange(Location now, Location after, int value) {
//        Location onceMovedRowByValue = now.plusRowBy(value);
//        Location TwiceMovedRowByValue = now.plusRowBy(value * 2);
//
//        boolean isOnceOrTwiceRowMoved =
//                onceMovedRowByValue.isSameRow(after) || TwiceMovedRowByValue.isSameRow(after);
//
//        return isOnceOrTwiceRowMoved && now.isSameCol(after);
//    }
}
