package chess.domain.board;

import chess.domain.piece.Score;

public class Result {

    private final Score blackTeamScore;
    private final Score whiteTeamScore;

    private Result(Score blackTeamScore, Score whiteTeamScore) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
    }

    public static Result generateResult(double blackTeamScore, double whiteTeamScore) {
        return new Result(new Score(blackTeamScore), new Score(whiteTeamScore));
    }

    public double getBlackTeamScore() {
        return blackTeamScore.getScore();
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore.getScore();
    }
}
