package chess.view.dto;

public class ChessStatusDto {

    private final double blackScore;
    private final double whiteScore;

    public ChessStatusDto(final double blackScore, final double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public static ChessStatusDto of(final double blackScore, double whiteScore) {
        return new ChessStatusDto(blackScore, whiteScore);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
