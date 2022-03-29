package chess.state;

import chess.board.Board;

public abstract class Finished extends Started {

    public Finished(Board board) {
        super(board);
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
