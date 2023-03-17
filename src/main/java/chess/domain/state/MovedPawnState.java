package chess.domain.state;

import chess.domain.ColorCompareResult;

public class MovedPawnState implements MoveState {

    private static final MovedPawnState instance = new MovedPawnState();

    private MovedPawnState() {
    }

    public static MovedPawnState getInstance() {
        return instance;
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
}
