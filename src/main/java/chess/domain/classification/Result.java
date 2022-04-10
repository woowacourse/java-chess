package chess.domain.classification;

public enum Result {

    WIN,
    LOSE,
    DRAW;

    public static Result competeScore(final double currentTeamScore, final double opponentTeamScore) {
        if (currentTeamScore > opponentTeamScore) {
            return WIN;
        }
        if (currentTeamScore < opponentTeamScore) {
            return LOSE;
        }
        return DRAW;
    }
}
