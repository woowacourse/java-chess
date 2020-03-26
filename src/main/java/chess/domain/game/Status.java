package chess.domain.game;

import chess.domain.piece.Color;

public class Status {
    private final double whiteScore;
    private final double blackScore;

    public Status(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Color winner() {
        if (whiteScore > blackScore) {
            return Color.WHITE;
        }
        if (blackScore > whiteScore) {
            return Color.BLACK;
        }
        return Color.NONE;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
