package chess.controller.dto;

public class ChessResultDto {
    private final double whiteScore;
    private final double blackScore;

    public ChessResultDto(final double whiteScore, final double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
