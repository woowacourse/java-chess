package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Running extends Started {
    public Running(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 진행 중 입니다.");
    }

    @Override
    public State end() {
        return new End(board());
    }

    @Override
    public boolean isNotFinished() {
        return true;
    }
}
