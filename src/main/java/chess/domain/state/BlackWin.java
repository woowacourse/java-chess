package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;

public class BlackWin extends Finished {

    protected BlackWin(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public Result winner() {
        return Result.BLACK;
    }

    @Override
    public StateType getStateType() {
        return StateType.BLACK_WIN;
    }
}
