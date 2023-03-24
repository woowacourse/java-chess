package chess.domain.game.state;

import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.position.ChessBoard;

public class PauseGame extends FinishedGame {

    protected PauseGame(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public GameResult calculateResult() {
        return new GameResult(MatchResult.PAUSE);
    }
}
