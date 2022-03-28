package chess.controller;

public class ScoresDto {

    private final String winner;
    private final ScoreResult scores;

    public ScoresDto(String winner, ScoreResult scores) {
        this.winner = winner;
        this.scores = scores;
    }

    public String getWinner() {
        return winner;
    }

    public ScoreResult getScores() {
        return scores;
    }
}
