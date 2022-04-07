package chess.domain.state;

import chess.dao.dto.StateType;
import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Result;

public class End extends Finished {

    public End(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public StateType getStateType() {
        return StateType.END;
    }

    @Override
    public Result winner() {
        double whiteScore = chessBoard.calculateScore(Color.WHITE);
        double blackScore = chessBoard.calculateScore(Color.BLACK);

        return Result.of(whiteScore, blackScore);
    }
}
