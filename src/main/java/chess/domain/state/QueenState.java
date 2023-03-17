package chess.domain.state;

import chess.domain.ColorCompareResult;

public class QueenState implements MoveState {

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        return isValidRequest(x, y) && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }

    private boolean isValidRequest(int x, int y) {
        if (x == 0 || y == 0) {
            return isValidStraightRequest(x, y);
        }
        return Math.abs(x) == Math.abs(y);
    }

    private boolean isValidStraightRequest(int x, int y) {
        if (x == 0 && y != 0) {
            return true;
        }
        return x != 0 && y == 0;
    }

    @Override
    public boolean canJump() {
        return false;
    }

}
