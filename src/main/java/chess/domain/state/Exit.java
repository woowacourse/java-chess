package chess.domain.state;

import chess.domain.position.Position;

public final class Exit extends State {

    Exit() {
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public Status status() {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }
}
