package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;

public class KnightState implements MoveState {
    @Override
    public MoveState move(int x, int y, ColorCompareResult colorCompareResult) {
        if (validMoveRequest(x, y) && ColorCompareResult.SAME_COLOR != colorCompareResult) {
            return this;
        }
        throw new IllegalPieceMoveException();
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
    public boolean canJump() {
        return true;
    }
}
