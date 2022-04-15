package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Result;

final class WhiteWin extends Finished {
    WhiteWin(Board board) {
        super(board);
    }

    @Override
    public Result getResult() {
        return Result.WHITE_WIN;
    }
}
