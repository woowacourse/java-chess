package chess.model;

import chess.model.piece.PieceColor;

public class Score {

    private final PieceColor color;
    private final double score;

    public Score(final PieceColor color, final double score) {
        this.color = color;
        this.score = score;
    }

    public PieceColor getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }
}
