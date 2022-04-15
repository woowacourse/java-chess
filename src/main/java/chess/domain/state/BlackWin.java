package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Result;

final class BlackWin extends Finished {
    BlackWin(Board board) {
        super(board);
    }

    @Override
    public Result getResult() {
        return Result.BLACK_WIN;
    }
}
