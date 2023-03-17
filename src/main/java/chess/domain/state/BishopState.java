package chess.domain.state;

import chess.domain.ColorCompareResult;

public class BishopState implements MoveState {

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        return Math.abs(x) == Math.abs(y) && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
