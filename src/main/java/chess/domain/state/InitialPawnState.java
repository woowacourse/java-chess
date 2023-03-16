package chess.domain.state;

import chess.domain.ColorCompareResult;

public class InitialPawnState implements MoveState {

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        if (isOneOrTwoStraightMove(x, y)) {
            return colorCompareResult == ColorCompareResult.EMPTY;
        }
        if (isOneDiagonalMove(x, y)) {
            return colorCompareResult == ColorCompareResult.DIFFERENT_COLOR;
        }
        return false;
    }

    private boolean isOneOrTwoStraightMove(int x, int y) {
        return x == 0 && (y == 1 || y == 2);
    }

    private boolean isOneDiagonalMove(int x, int y) {
        return Math.abs(x) == 1 && y == 1;
    }

    @Override
    public MoveState getNextState() {
        return new MovedPawnState();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}

