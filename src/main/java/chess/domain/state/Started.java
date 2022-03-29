package chess.domain.state;

import chess.domain.Board;

public abstract class Started implements State {

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
    public Board board() {
        return board;
    }
}
