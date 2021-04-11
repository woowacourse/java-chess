package chess.domain.game;

import chess.domain.Score;
import chess.domain.TeamColor;

public class ChessResult {
    private final Score whiteTeamScore;
    private final Score blackTeamScore;

    public ChessResult(Score whiteTeamScore, Score blackTeamScore) {
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
    }

    public TeamColor getWinner() {
        if (whiteTeamScore.value() > blackTeamScore.value()) {
            return TeamColor.WHITE;
        }
        return TeamColor.BLACK;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore.value();
    }

    public double getBlackTeamScore() {
        return blackTeamScore.value();
    }
}
