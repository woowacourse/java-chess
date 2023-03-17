package chess.domain.state;

import chess.domain.ColorCompareResult;

public class RookState implements MoveState {

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        return isValidVariance(x, y) && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }

    private boolean isValidVariance(int x, int y) {
        if (x != 0 && y == 0) {
            return true;
        }
        return x == 0 && y != 0;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
