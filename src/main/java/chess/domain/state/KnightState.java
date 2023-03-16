package chess.domain.state;

import chess.domain.ColorCompareResult;

public class KnightState implements MoveState {

    private static final KnightState instance = new KnightState();

    private KnightState() {
    }

    public static KnightState getInstance() {
        return instance;
    }

    private boolean validMoveRequest(int x, int y) {
        if (Math.abs(x) == 2 && Math.abs(y) == 1) {
            return true;
        }

        if (Math.abs(x) == 1 && Math.abs(y) == 2) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        if (validMoveRequest(x, y) && ColorCompareResult.SAME_COLOR != colorCompareResult) {
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
        return true;
    }
}
