package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;

public class MovedPawnState implements MoveState {
    @Override
    public MoveState move(int x, int y, ColorCompareResult colorCompareResult) {
        if (x == 0) {
            straightMove(y, colorCompareResult);
            return this;
        }
        if (Math.abs(x) == 1 && y == 1) {
            diagonalMove(colorCompareResult);
            return this;
        }
        throw new IllegalPieceMoveException();
    }

    private void diagonalMove(ColorCompareResult colorCompareResult) {
        if (colorCompareResult != ColorCompareResult.DIFFERENT_COLOR) {
            throw new IllegalPieceMoveException();
        }
    }

    private void straightMove(int y, ColorCompareResult colorCompareResult) {
        if ((y == 1) && colorCompareResult == ColorCompareResult.EMPTY) {
            return;
        }
        throw new IllegalPieceMoveException();
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
