package chess.domain.state;

import chess.domain.ColorCompareResult;

public class EmptyState implements MoveState {

    @Override
    public MoveState move(int x, int y, ColorCompareResult colorCompareResult) {
        throw new IllegalStateException("초기화 되지 않은 객체입니다");
    }

    @Override
    public boolean canJump() {
        throw new IllegalStateException("초기화 되지 않은 객체입니다");
    }
}
