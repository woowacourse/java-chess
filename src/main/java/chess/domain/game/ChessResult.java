package chess.domain.game;

import chess.domain.Score;

public class ChessResult {
    private final Score whiteTeamScore;
    private final Score blackTeamScore;

    public ChessResult(Score whiteTeamScore, Score blackTeamScore) {
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore.value();
    }

    public double getBlackTeamScore() {
        return blackTeamScore.value();
    }
}
