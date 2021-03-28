package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Running extends AfterStart {

    public Running(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new IllegalStateException("이미 체스게임이 진행 중 입니다.");
    }

    @Override
    public Board board() {
        return afterStartBoard();
    }

    @Override
    public String winner() {
        throw new IllegalStateException("아직 체스게임이 진행중 입니다.");
    }

    @Override
    public State end() {
        return new End(afterStartBoard());
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
