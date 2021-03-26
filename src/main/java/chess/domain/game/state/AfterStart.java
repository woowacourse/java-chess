package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class AfterStart implements State {

    private final Board board;

    public AfterStart(Board board) {
        this.board = board;
    }

    protected Board board() {
        return this.board;
    }
}
