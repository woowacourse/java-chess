package chess.domain.state;

import chess.domain.ColorCompareResult;

public class PawnState implements MoveState {
    private boolean moved = false;

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        if (!moved) {
            return initialMoveState(x, y, colorCompareResult);
        }

        return afterInitialMoved(x, y, colorCompareResult);
    }

    private boolean initialMoveState(int x, int y, ColorCompareResult colorCompareResult) {
        if (isStraightMoveOf(x, y, 2)) {
            moved = true;
            return colorCompareResult == ColorCompareResult.EMPTY;
        }
        if (isOneDiagonalMove(x, y)) {
            moved = true;
            return colorCompareResult == ColorCompareResult.DIFFERENT_COLOR;
        }

        return false;
    }

    private boolean afterInitialMoved(int x, int y, ColorCompareResult colorCompareResult) {
        if (isStraightMoveOf(x, y, 1)) {
            return colorCompareResult == ColorCompareResult.EMPTY;
        }
        if (isOneDiagonalMove(x, y)) {
            return colorCompareResult == ColorCompareResult.DIFFERENT_COLOR;
        }
        return false;
    }

    private boolean isStraightMoveOf(int x, int y, int size) {
        return x == 0 && (1 <= y && y <= size);
    }

    private boolean isOneDiagonalMove(int x, int y) {
        return Math.abs(x) == 1 && y == 1;
    }


    @Override
    public boolean canJump() {
        return false;
    }
}
