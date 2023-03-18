package chess.domain.state;

import chess.domain.ColorCompareResult;
import chess.domain.exception.IllegalPieceMoveException;

public class EmptyState implements MoveState {

    private static final EmptyState instance = new EmptyState();

    private EmptyState() {
    }

    public static EmptyState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        throw new IllegalPieceMoveException();
    }

    @Override
    public MoveState getNextState() {
        throw new IllegalPieceMoveException();
    }
}
