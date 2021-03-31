package chess.dto;

public class ScoreDto {
    private final String score;

    public ScoreDto(double score) {
        this.score = Double.toString(score);
    }

    public String getScore() {
        return score;
    }
}
