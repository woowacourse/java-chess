package chess.domain.state;

import chess.domain.pieces.component.Team;

public class End implements State{
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
        throw new IllegalStateException();
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("end 상태에선 차례가 없습니다.");
    }
}
