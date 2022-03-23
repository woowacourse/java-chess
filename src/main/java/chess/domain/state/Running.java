package chess.domain.state;

import chess.domain.Board;

public abstract class Running implements State {
    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 실행중입니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State end() {
        return new End();
    }
}
