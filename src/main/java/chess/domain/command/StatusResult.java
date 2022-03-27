package chess.domain.command;

import chess.domain.board.ScoreResult;

public class StatusResult {

    private final ScoreResult result;

    public StatusResult(ScoreResult result) {
        this.result = result;
    }

    public double getWhiteScore() {
        return result.getWhiteScore().get();
    }

    public double getBlackScore() {
        return result.getBlackScore().get();
    }
}
