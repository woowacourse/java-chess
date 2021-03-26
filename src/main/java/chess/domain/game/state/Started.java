package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Color;

import java.util.List;

public abstract class Started implements State {
    private final Board board;

    public Started(Board board) {
        this.board = board;
    }

    protected Board board() {
        return this.board;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public State passTurn() {
        throw new IllegalStateException("현재 수행할 수 없는 명령입니다.");
    }

    @Override
    public List<Rank> ranks() {
        return board.ranks();
    }

    @Override
    public Color winner() {
        throw new IllegalStateException("현재 수행할 수 없는 명령입니다.");
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
