package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Result;

public class End extends Finished {

    protected End(ChessBoard chessBoard) {
      super(chessBoard);
    }

    @Override
    public Result winner() {
        double whiteScore = chessBoard.calculateScore(Color.WHITE);
        double blackScore = chessBoard.calculateScore(Color.BLACK);

        return Result.of(whiteScore, blackScore);
    }

    @Override
    public StateType getStateType() {
        return StateType.END;
    }
}
