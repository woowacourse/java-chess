package chess.dto.response;

public final class StatusDto {

    private final double blackScore;
    private final double whiteScore;
    private final String winner;

    private StatusDto(double blackScore, double whiteScore, String winner) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.winner = winner;
    }

    public static StatusDto of(double blackScore, double whiteScore, String winner) {
        return new StatusDto(blackScore, whiteScore, winner);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public String getWinner() {
        return winner;
    }
}
