package chess.domain.state;

import chess.domain.Status;

public abstract class Running extends State {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public Status status() {
        throw new IllegalStateException("게임이 끝나지 않았습니다.");
    }
}
