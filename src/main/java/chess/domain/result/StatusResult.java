package chess.domain.result;

import chess.domain.Team;
import chess.domain.Winner;

public class StatusResult {

    private final double blackScore;
    private final double whiteScore;
    private final Team winner;

    public StatusResult(double blackScore, double whiteScore, Winner winner) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.winner = winner.getTeam();
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public Team getWinner() {
        return winner;
    }
}
