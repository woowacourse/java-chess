package chess.domain.state;

import chess.exception.DomainException;

public class EndWithoutGame implements State {
    public EndWithoutGame() {
        super();
    }

    @Override
    public State start() {
        throw new DomainException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State end() {
        throw new DomainException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State status() {
        throw new DomainException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State move(boolean isKingDead) {
        throw new DomainException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public boolean isNotRunning() {
        throw new DomainException("시스템 에러 - 잘못된 상태 호출입니다.");
    }
}
