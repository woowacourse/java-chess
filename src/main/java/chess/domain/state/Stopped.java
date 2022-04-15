package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Result;

public class Stopped extends Finished {

    Stopped(Board board) {
        super(board);
    }

    @Override
    public Result getResult() {
        return Result.DRAW;
    }
}

