package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import java.util.List;

public abstract class Running extends AfterStart {

    public Running(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new IllegalStateException("이미 체스게임이 진행 중 입니다.");
    }

    @Override
    public List<Rank> ranks() {
        return board().ranks();
    }

    @Override
    public String winner() {
        throw new IllegalStateException("아직 체스게임이 진행중 입니다.");
    }

    @Override
    public State end() {
        return new End(board());
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
