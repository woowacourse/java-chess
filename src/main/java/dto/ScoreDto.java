package dto;

public class ScoreDto {

    private final double whiteScore;
    private final double blackScore;
    private final String winner;

    private ScoreDto(final double whiteScore, final double blackScore, final String winner) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
    }

    public static ScoreDto of(final double whiteScore, final double blackScore) {
        final String winner = findWinner(whiteScore, blackScore);
        return new ScoreDto(whiteScore, blackScore, winner);
    }

    private static String findWinner(final double whiteScore, final double blackScore) {
        if (whiteScore > blackScore) {
            return "White Win";
        }
        if (whiteScore == blackScore) {
            return "Draw";
        }
        return "Black Win";
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public String getWinner() {
        return winner;
    }
}
