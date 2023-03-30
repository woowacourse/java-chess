package chess.domain.game;

import chess.domain.board.BlackScore;
import chess.domain.board.WhiteScore;

public class Result {
    private final GameResult gameResult;
    private final WhiteScore whiteScore;
    private final BlackScore blackScore;

    public Result(WhiteScore whiteScore, BlackScore blackScore) {
        gameResult = GameResult.getGameResultOf(whiteScore, blackScore);
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public WhiteScore getWhiteScore() {
        return whiteScore;
    }

    public BlackScore getBlackScore() {
        return blackScore;
    }
}
