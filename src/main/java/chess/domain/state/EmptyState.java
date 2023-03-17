package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;

public class EmptyState implements MoveState {

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        throw new IllegalPieceMoveException();
    }

    @Override
    public boolean canJump() {
        throw new IllegalPieceMoveException();
    }
}
