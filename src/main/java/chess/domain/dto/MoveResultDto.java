package chess.domain.dto;

public class MoveResultDto {
    private final String turn;
    private final double blackScore;
    private final double whiteScore;

    public MoveResultDto(String turn, double blackScore, double whiteScore) {
        this.turn = turn;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public String getTurn() {
        return turn;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
