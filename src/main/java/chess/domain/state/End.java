package chess.domain.state;

import chess.domain.position.Position;

public class End extends State {

    @Override
    public State start() {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임이 이미 종료됐습니다.");
    }

    @Override
    public void status() {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isEnded() {
        return true;
    }
}
