package dto;

import domain.piece.Camp;
import domain.piece.Score;

public class ScoreDto {
    private final String camp;
    private final double score;

    private ScoreDto(String camp, double score) {
        this.camp = camp;
        this.score = score;
    }

    public static ScoreDto from(Camp camp, Score score) {
        return new ScoreDto(camp.name(), score.getScore());
    }

    public double getScore() {
        return score;
    }

    public String getCamp() {
        return camp;
    }
}
