package chess.domain.state;

import chess.domain.ColorCompareResult;

public class EmptyState implements MoveState {
    private static final EmptyState instance = new EmptyState();

    private EmptyState() {
    }

    public static EmptyState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int x, int y, ColorCompareResult colorCompareResult) {
        throw new IllegalStateException("초기화 되지 않은 객체입니다");
    }

    @Override
    public MoveState getNextState() {
        throw new IllegalStateException("초기화 되지 않은 객체입니다");
    }

    @Override
    public boolean canJump() {
        throw new IllegalStateException("초기화 되지 않은 객체입니다");
    }
}
