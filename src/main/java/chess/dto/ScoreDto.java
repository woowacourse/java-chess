package chess.dto;

import chess.domain.game.Color;

public class ScoreDto {
    private final Color color;
    private final double score;

    public ScoreDto(Color color, double score) {
        this.color = color;
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
