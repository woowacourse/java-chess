package chess.domain.state;

import chess.domain.pieces.component.Team;

public class End implements State {
    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public void move(Runnable runnable) {
        throw new IllegalStateException();
    }

    @Override
    public Team getTurn() {
        throw new IllegalStateException("end 상태에는 턴이 존재하지 않습니다.");
    }

    @Override
    public void checkStartState() {
        throw new IllegalStateException("end 상태에선 차례가 없습니다.");
    }

    @Override
    public State changeState() {
        throw new IllegalStateException("end는 상태를 바꿀 수 없습니다.");
    }
}
