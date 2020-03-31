package chess.domain.game;

import chess.domain.piece.Color;

public class Status {
    private final Score whiteScore;
    private final Score blackScore;

    public Status(Score whiteScore, Score blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Color winner() {
        if (whiteScore.isHigherThan(blackScore)) {
            return Color.WHITE;
        }
        if (blackScore.isHigherThan(whiteScore)) {
            return Color.BLACK;
        }
        return Color.NONE;
    }

    public double getWhiteScore() {
        return whiteScore.getValue();
    }

    public double getBlackScore() {
        return blackScore.getValue();
    }
}
