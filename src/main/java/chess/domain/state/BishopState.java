package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;

public class BishopState implements MoveState {
    @Override
    public MoveState move(int x, int y, ColorCompareResult colorCompareResult) {
        if (Math.abs(x) == Math.abs(y) && colorCompareResult != ColorCompareResult.SAME_COLOR) {
            return this;
        }
        throw new IllegalPieceMoveException();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
