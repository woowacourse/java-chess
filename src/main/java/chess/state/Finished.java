package chess.state;

import chess.chessboard.Board;

public abstract class Finished extends Started {

    public Finished(final Board board) {
        super(board);
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}

