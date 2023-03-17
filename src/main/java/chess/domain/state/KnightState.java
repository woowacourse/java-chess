package chess.domain.state;

import chess.domain.ColorCompareResult;

public class KnightState implements MoveState {

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        return validMoveRequest(x, y) && ColorCompareResult.SAME_COLOR != colorCompareResult;
    }

    private boolean validMoveRequest(int x, int y) {
        if (Math.abs(x) == 2 && Math.abs(y) == 1) {
            return true;
        }
        return Math.abs(x) == 1 && Math.abs(y) == 2;
    }

    @Override
    public boolean canJump() {
        return true;
    }
}
