package chess.domain.state;

import chess.domain.Color;

public class MoveState implements GameState {
    private final Color color;

    public MoveState(Color color) {
        this.color = color;
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("이미 시작한 상태입니다.");
    }

    @Override
    public GameState move() {
        Color nextTurnColor = color.getOpposite();
        return new MoveState(nextTurnColor);
    }

    @Override
    public GameState end() {
        return new EndState();
    }

    @Override
    public boolean isPlaying() {
        return true;
    }
}
