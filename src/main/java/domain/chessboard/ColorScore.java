package domain.chessboard;

import domain.piece.Color;

public class ColorScore {

    private final Color color;
    private final double score;

    public ColorScore(Color color, double score) {
        this.color = color;
        this.score = score;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }

}
