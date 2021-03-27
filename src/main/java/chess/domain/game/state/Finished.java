package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import java.util.List;

public abstract class Finished extends AfterStart {

    public Finished(Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new Init(board());
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        throw new IllegalStateException("이미 체스게임이 종료되었습니다.");
    }

    @Override
    public State passTurn() {
        throw new IllegalStateException("이미 체스게임이 종료되었습니다.");
    }

    @Override
    public List<Rank> ranks() {
        return board().ranks();
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
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
