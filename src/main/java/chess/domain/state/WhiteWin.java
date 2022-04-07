package chess.domain.state;

import chess.dao.dto.StateType;
import chess.domain.ChessBoard;
import chess.domain.Result;

public class WhiteWin extends Finished {

    public WhiteWin(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public StateType getStateType() {
        return StateType.WHITE_WIN;
    }

    @Override
    public Result winner() {
        return Result.WHITE;
    }
}
