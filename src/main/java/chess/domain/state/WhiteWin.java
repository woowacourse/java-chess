package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;

public class WhiteWin extends Finished {

    protected WhiteWin(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public Result winner() {
        return Result.WHITE;
    }

    @Override
    public StateType getStateType() {
        return StateType.WHITE_WIN;
    }
}
