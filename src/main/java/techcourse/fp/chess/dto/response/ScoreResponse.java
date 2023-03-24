package techcourse.fp.chess.dto.response;

import techcourse.fp.chess.domain.piece.Color;

public class ScoreResponse {

    private final String color;
    private final double score;

    public ScoreResponse(final String color, final double score) {
        this.color = color;
        this.score = score;
    }

    public static ScoreResponse of(final Color color, final double score) {
        return new ScoreResponse(color.name(), score);
    }

    public String getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }
}
