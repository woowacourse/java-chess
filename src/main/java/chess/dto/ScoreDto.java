package chess.dto;

import chess.domain.Score;
import chess.domain.piece.PieceColor;

public class ScoreDto {

    private final String name;
    private final double score;

    private ScoreDto(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public static ScoreDto of(PieceColor color, Score score) {
        return new ScoreDto(color.name(), score.getValue());
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
