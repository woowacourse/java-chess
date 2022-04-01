package chess.model.state.finished;

import chess.model.board.Board;
import chess.model.board.GameResult;

public final class Status extends Finished {

    private final Board board;

    public Status(Board board) {
        this.board = board;
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public GameResult getScore() {
        return board.calculateScore();
    }
}
