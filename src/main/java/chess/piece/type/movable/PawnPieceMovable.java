package chess.piece.type.movable;

import chess.location.Location;
import chess.location.Row;
import chess.piece.type.Piece;
import chess.team.Team;

import java.util.Map;

public class PawnPieceMovable implements PieceMovable {
    private static final Row WHITE_PAWN_ROW = Row.of(2);
    private static final Row BLACK_PAWN_ROW = Row.of(7);

    private final Team team;

    public PawnPieceMovable(Team team) {
        this.team = team;
    }

    @Override
    public boolean canMove(Map<Location, Piece> board, Location now, Location after) {
        int value = 1;
        if (team.isBlack()) {
            value = -1;
        }
        return canMoveDiagonal(board, now, after, value)
                || canMoveInInitialPawnLocation(board, now, after, value)
                || canMoveOnce(board, now, after, value);
    }

    private boolean canMoveOnce(Map<Location, Piece> board, Location now, Location after, int value) {
        return isPawnForwardRange(now, after, value) && hasNotObstacle(board, now, after);
    }

    private boolean isPawnForwardRange(Location now, Location after, int value) {
        Location movedRowByValue = now.plusRowBy(value);

        return movedRowByValue.isSameRow(after)
                && movedRowByValue.isSameCol(after);
    }

    private boolean canMoveDiagonal(Map<Location, Piece> board, Location now, Location after, int value) {
        return now.isForwardDiagonal(after, value) && canMoveDiagonal(board, now, after);
    }

    private boolean canMoveInInitialPawnLocation(Map<Location, Piece> board, Location now, Location after, int value) {
        System.out.println("isInitialPawnLocation : " + isInitialPawnLocation(now, team));
        System.out.println("isInitialPawnForwardRange : " + isInitialPawnForwardRange(now, after, value));
        System.out.println("hasNotObstacle : " + hasNotObstacle(board, now, after));
        return isInitialPawnLocation(now, team)
                && isInitialPawnForwardRange(now, after, value)
                && hasNotObstacle(board, now, after);
    }

    @Override
    public boolean hasNotObstacle(Map<Location, Piece> board, Location now, Location after) {
        Location nextLocation = now.calculateNextLocation(after, 1);
        boolean isMoveByOnceRowCommand = Math.abs(now.getRowValue() - after.getRowValue()) == 1;
        if (isMoveByOnceRowCommand && board.containsKey(nextLocation)) {
            return false;
        }
        boolean isMoveByTwiceRowCommand = Math.abs(now.getRowValue() - after.getRowValue()) == 2;
        boolean hasNotObstacle = board.containsKey(nextLocation) || board.containsKey(after);

        return !isMoveByTwiceRowCommand || !hasNotObstacle;
    }

    private boolean canMoveDiagonal(Map<Location, Piece> board, Location now, Location after) {
        if (isDiagonalAndIsReverseTeam(board, now, after)) {
            Piece maybeEnemyPiece = board.get(after);
            return maybeEnemyPiece.isReverseTeam(team);
        }
        return now.isDiagonal(after);
    }

    private boolean isDiagonalAndIsReverseTeam(Map<Location, Piece> board, Location now, Location after) {
        return now.isDiagonal(after) && board.containsKey(after);
    }

    private boolean isInitialPawnLocation(Location location, Team team) {
        if (team.isBlack()) {
            return location.isSameRow(BLACK_PAWN_ROW);
        }
        System.out.println("값은 " + location.isSameRow(WHITE_PAWN_ROW));
        System.out.println(location.getRowValue());
        return location.isSameRow(WHITE_PAWN_ROW);
    }

    private boolean isInitialPawnForwardRange(Location now, Location after, int value) {
        Location onceMovedRowByValue = now.plusRowBy(value);
        Location TwiceMovedRowByValue = now.plusRowBy(value * 2);

        boolean isOnceOrTwiceRowMoved =
                onceMovedRowByValue.isSameRow(after) || TwiceMovedRowByValue.isSameRow(after);

        return isOnceOrTwiceRowMoved && now.isSameCol(after);
    }
}
