package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;

public class RookState implements MoveState {

    @Override
    public MoveState move(int x, int y, ColorCompareResult colorCompareResult) {
        if (validMoveRequest(x, y) && colorCompareResult != ColorCompareResult.SAME_COLOR) {
            return this;
        }
        throw new IllegalPieceMoveException();
    }

    private boolean validMoveRequest(int x, int y) {
        if (x != 0 && y == 0) {
            return true;
        }
        if (x == 0 && y != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
