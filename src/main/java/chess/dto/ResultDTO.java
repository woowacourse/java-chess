package chess.dto;

import chess.domain.piece.TeamType;
import chess.domain.result.Scores;

public class ResultDTO {

    private final double blackTeamScore;
    private final double whiteTeamScore;
    private final TeamType winnerTeamType;

    public ResultDTO(double blackTeamScore, double whiteTeamScore, TeamType winnerTeamType) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
        this.winnerTeamType = winnerTeamType;
    }

    public static ResultDTO of(Scores scores, TeamType winnerTeamType) {
        return new ResultDTO(scores.getBlackTeamScore(), scores.getWhiteTeamScore(), winnerTeamType);
    }

    public double getBlackTeamScore() {
        return blackTeamScore;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }

    public TeamType getWinnerTeamType() {
        return winnerTeamType;
    }
}
