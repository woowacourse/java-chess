package domain.piece;

import domain.Location;
import domain.type.direction.PieceMoveDirection;
import java.util.List;

public class MoveCheckStrategy {

    private static final int SAME_LOCATION = 0;
    private static final int MIN_ROW_DIFFERENCE = 1;
    private static final int MAX_ROW_DIFFERENCE = 2;

    public static boolean isBishopMove(final Location start, final Location end) {
        return start.isDiagonal(end);
    }

    public static boolean isRookMove(final Location start, final Location end) {
        return start.isSameLine(end);
    }

    public static boolean isQueenMove(final Location start, final Location end) {
        return start.isSameLine(end) || start.isDiagonal(end);
    }

    public static boolean isKingMove(final Location start, final Location end) {
        if (!isQueenMove(start, end)) {
            return false;
        }
        final int rowDifference = start.getRow() - end.getRow();
        final int colDifference = start.getCol() - end.getCol();
        if (rowDifference == SAME_LOCATION && colDifference == SAME_LOCATION) {
            return false;
        }
        final List<Integer> ableDifference = List.of(-1, 0, 1);
        return ableDifference.contains(rowDifference) && ableDifference.contains(colDifference);
    }

    public static boolean isKnightMove(final Location start, final Location end) {
        PieceMoveDirection direction = PieceMoveDirection.find(start, end);
        return direction.isNightMoveDirection();
    }

    public static boolean isPawnMove(final Location start, final Location end) {
        int absoluteRowDifference = Math.abs(start.getRow() - end.getRow());
        return start.isSameCol(end)
            && MIN_ROW_DIFFERENCE <= absoluteRowDifference
            && absoluteRowDifference <= MAX_ROW_DIFFERENCE;
    }
}
