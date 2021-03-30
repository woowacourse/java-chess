package chess.dto;

import chess.domain.piece.TeamType;
import chess.domain.result.Result;

public class ResultDTO {

    private final double blackTeamScore;
    private final double whiteTeamScore;
    private final TeamType winnerTeamType;

    public ResultDTO(double blackTeamScore, double whiteTeamScore, TeamType winnerTeamType) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
        this.winnerTeamType = winnerTeamType;
    }

    public static ResultDTO from(Result result, TeamType winnerTeamType) {
        return new ResultDTO(result.getBlackTeamScore(), result.getWhiteTeamScore(), winnerTeamType);
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
