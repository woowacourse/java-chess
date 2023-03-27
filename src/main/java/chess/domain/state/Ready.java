package chess.domain.state;

import chess.domain.pieces.component.Team;

public class Ready implements State{
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
        throw new IllegalStateException("start 상태에서는 차례가 없습니다.");
    }

    @Override
    public void startGame() {
    }
}
