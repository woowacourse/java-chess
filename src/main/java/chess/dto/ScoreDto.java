package chess.dto;

import chess.domain.Color;

public class ScoreDto {
    private final double blackScore;
    private final double whiteScore;
    private final Color winner;

    private ScoreDto(final double blackScore, final double whiteScore, final Color winner) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.winner = winner;
    }

    public static ScoreDto of(final double blackScore, final double whiteScore) {
        return new ScoreDto(blackScore, whiteScore, Color.EMPTY);
    }

    public static ScoreDto of(final double blackScore, final double whiteScore, final Color color) {
        return new ScoreDto(blackScore, whiteScore, color);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public Color getWinner() {
        return winner;
    }
}
