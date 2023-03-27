package chess.domain.state;

import chess.domain.pieces.component.Team;

public class BlackTurn implements State {
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public void move(Runnable runnable) {
        runnable.run();
    }

    @Override
    public Team getTurn() {
        return Team.BLACK;
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("게임이 이미 실행중입니다.");
    }
}
