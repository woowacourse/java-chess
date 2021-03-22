package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Running extends Started {
    public Running(Board board) {
        super(board);
    }

    public boolean isFinished() {
        return !board().isAliveBothKings();
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 진행 중 입니다.");
    }
}
