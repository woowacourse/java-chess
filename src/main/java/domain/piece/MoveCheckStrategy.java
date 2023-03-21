package domain.piece;

import static domain.type.direction.PieceMoveDirection.DOWN;
import static domain.type.direction.PieceMoveDirection.LEFT_DOWN;
import static domain.type.direction.PieceMoveDirection.LEFT_UP;
import static domain.type.direction.PieceMoveDirection.RIGHT_DOWN;
import static domain.type.direction.PieceMoveDirection.RIGHT_UP;
import static domain.type.direction.PieceMoveDirection.UP;

import domain.Location;
import domain.type.direction.PieceMoveDirection;
import java.util.List;

public class MoveCheckStrategy {

    private static final int STAY = 0;
    private static final int WHITE_PAWN_INITIAL_ROW = 1;
    private static final int BLACK_PAWN_INITIAL_ROW = 6;
    private static final List<PieceMoveDirection> WHITE_VALID_DIAGONAL_MOVE = List.of(RIGHT_UP, LEFT_UP);
    private static final List<PieceMoveDirection> BLACK_VALID_DIAGONAL_MOVE = List.of(RIGHT_DOWN, LEFT_DOWN);

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
        if (rowDifference == STAY && colDifference == STAY) {
            return false;
        }
        final List<Integer> validDifferences = List.of(-1, 0, 1);
        return validDifferences.contains(rowDifference) && validDifferences.contains(colDifference);
    }

    public static boolean isKnightMove(final Location start, final Location end) {
        PieceMoveDirection direction = PieceMoveDirection.find(start, end);
        return direction.isNightMoveDirection();
    }

    public static boolean isWhitePawnMove(final Location start, final Location end) {
        PieceMoveDirection direction = PieceMoveDirection.find(start, end);
        int rowDifference = end.getRow() - start.getRow();
        if (direction == UP && rowDifference == 1) {
            return true;
        }
        if (direction == UP && start.getRow() == WHITE_PAWN_INITIAL_ROW && rowDifference == 2) {
            return true;
        }
        return WHITE_VALID_DIAGONAL_MOVE.contains(direction) && rowDifference == 1;
    }

    public static boolean isBlackPawnMove(final Location start, final Location end) {
        PieceMoveDirection direction = PieceMoveDirection.find(start, end);
        int rowDifference = end.getRow() - start.getRow();
        if (direction == DOWN && rowDifference == -1) {
            return true;
        }
        if (direction == DOWN && start.getRow() == BLACK_PAWN_INITIAL_ROW && rowDifference == -2) {
            return true;
        }
        return BLACK_VALID_DIAGONAL_MOVE.contains(direction) && rowDifference == -1;
    }
}
