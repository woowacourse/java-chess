package chess.dto;

public class WebStatusDto {

    private final double whiteScore;
    private final double blackScore;
    private final String winner;

    public WebStatusDto(double whiteScore, double blackScore, String winner) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winner = winner;
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
