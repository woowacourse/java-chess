package chess.dto;

public class GameResultDto {

    private final String winner;
    private final double whiteScore;
    private final double blackScore;

    public GameResultDto(String winner, double whiteScore, double blackScore) {
        this.winner = winner;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public String getWinner() {
        return winner;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

}
