package chess.domain.gamestate;

import chess.domain.board.Board;

public abstract class AbstractState implements State {

    protected final Board board;

    public AbstractState(Board board) {
        this.board = board;
    }
}
