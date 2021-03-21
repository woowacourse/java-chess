package chess.domain.game.state;

import chess.domain.board.Board;

public abstract class Running extends Started {

    public Running(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new IllegalStateException();
    }

    @Override
    public State end() {
        return new End(board());
    }
}
