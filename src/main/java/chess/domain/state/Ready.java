package chess.domain.state;

import chess.domain.pieces.component.Team;

public class Ready implements State {
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public void move(Runnable runnable) {
        throw new IllegalStateException("start를 먼저 실행해 주세요");
    }

    @Override
    public Team getTurn() {
        throw new IllegalStateException("준 상태에는 턴이 존재하지 않습니다.");
    }

    @Override
    public void checkStartState() {
    }

    @Override
    public State changeState() {
        return new WhiteTurn();
    }
}
