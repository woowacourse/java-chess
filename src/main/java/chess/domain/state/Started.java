package chess.domain.state;

import chess.domain.Board;
import chess.domain.Score;

public abstract class Started implements State {

    protected static final int SOURCE_INDEX = 0;
    protected static final int TARGET_INDEX = 1;

    private final Board board;

    public Started(Board board) {
        this.board = board;
    }

   @Override
    public State start() {
        throw new IllegalArgumentException("현재 상태에서는 start할 수 없습니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public Score status() {
        return Score.from(board());
    }

    @Override
    public Board board() {
        return board;
    }
}
