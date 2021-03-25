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
    public String finishReason() {
        throw new IllegalStateException("아직 게임이 종료되지 않았습니다.");
    }

    @Override
    public boolean isRunning() {
        return board().isAliveBothKings();
    }
}
