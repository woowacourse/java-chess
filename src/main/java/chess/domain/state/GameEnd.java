package chess.domain.state;

import chess.exception.DomainException;

public class GameEnd implements State {
    public GameEnd() {
        super();
    }

    @Override
    public State start() {
        return new Running();
    }

    @Override
    public State end() {
        throw new DomainException("종료할 수 없습니다. - 실행중인 게임이 없습니다.");
    }

    @Override
    public State status() {
        return this;
    }

    @Override
    public State move(boolean isKingDead) {
        throw new DomainException("이동 명령을 수행할 수 없습니다. - 실행중인 게임이 없습니다.");
    }

    @Override
    public boolean isNotRunning() {
        return true;
    }
}
