package chess.state;

import chess.Board;

public abstract class Running extends Started {

    public Running(Board board) {
        super(board);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
