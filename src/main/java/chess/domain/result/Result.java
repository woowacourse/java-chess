package chess.domain.result;

import chess.domain.piece.TeamType;

public class Result {

    private final Scores scores;
    private final TeamType winnerTeamType;

    public Result(Scores scores, TeamType winnerTeamType) {
        this.scores = scores;
        this.winnerTeamType = winnerTeamType;
    }

    public Scores getScores() {
        return scores;
    }

    public TeamType getWinnerTeamType() {
        return winnerTeamType;
    }
}
