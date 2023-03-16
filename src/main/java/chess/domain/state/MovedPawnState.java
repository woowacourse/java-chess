package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;

public class MovedPawnState implements MoveState {

    private void straightMove(int y, ColorCompareResult colorCompareResult) {
        if ((y == 1) && colorCompareResult == ColorCompareResult.EMPTY) {
            return;
        }
        throw new IllegalPieceMoveException();
    }

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        if (isOneStraightMove(x, y)) {
            return colorCompareResult == ColorCompareResult.EMPTY;
        }
        if (isOneDiagonalMove(x, y)) {
            return colorCompareResult == ColorCompareResult.DIFFERENT_COLOR;
        }
        return false;
    }

    private boolean isOneStraightMove(int x, int y) {
        return x == 0 && y == 1;
    }

    private boolean isOneDiagonalMove(int x, int y) {
        return Math.abs(x) == 1 && y == 1;
    }

    @Override
    public MoveState getNextState() {
        return this;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
