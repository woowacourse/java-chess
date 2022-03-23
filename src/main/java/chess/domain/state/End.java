package chess.domain.state;

import chess.domain.Board;

public class End implements State {
    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State end() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }

    @Override
    public State move() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
    }
}
