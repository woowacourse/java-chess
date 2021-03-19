package chess.domain;

public class ChessResult {
    private final Score whiteTeamScore;
    private final Score blackTeamScore;

    public ChessResult(Score whiteTeamScore, Score blackTeamScore) {
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
    }

    public Score whiteTeamScore() {
        return whiteTeamScore;
    }

    public Score blackTeamScore() {
        return blackTeamScore;
    }

    public TeamColor winner() {
        if (whiteTeamScore.isBiggerThan(blackTeamScore)) {
            return TeamColor.WHITE;
        }
        return TeamColor.BLACK;
    }
}
