package chess.domain.state;

import chess.domain.ColorCompareResult;

public class QueenState implements MoveState {
    private static final QueenState instance = new QueenState();

    private QueenState() {
    }

    public static QueenState getInstance() {
        return instance;
    }

    private boolean validMoveRequest(int x, int y) {
        if (x == 0 || y == 0) {
            return straightMove(x, y);
        }

        if (Math.abs(x) == Math.abs(y)) {
            return true;
        }

        return false;
    }

    private boolean straightMove(int x, int y) {
        if (x == 0 && y != 0) {
            return true;
        }

        if (x != 0 && y == 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        if (validMoveRequest(x, y) && colorCompareResult != ColorCompareResult.SAME_COLOR) {
            return true;
        }
        return false;
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
