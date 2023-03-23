package chess.domain.game;

import chess.domain.board.Score;

public class Result {
    private final GameResult gameResult;
    private final Score whiteScore;
    private final Score blackScore;

    public Result(Score whiteScore, Score blackScore) {
        gameResult = GameResult.getGameResultOf(whiteScore, blackScore);
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public Score getWhiteScore() {
        return whiteScore;
    }

    public Score getBlackScore() {
        return blackScore;
    }
}
