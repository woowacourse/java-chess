package chess.domain.state;

import chess.domain.Board;
import chess.domain.Position;

public abstract class State {
    protected final Board board;

    protected State(final Board board) {
        this.board = board;
    }

    public abstract boolean isEnd();

    public abstract State move(Position source, Position target);

    public abstract State start();

    public abstract State end();
}
