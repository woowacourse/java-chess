package chess.controller.dto;

public class ScoreDto {

    private double score;

    public ScoreDto(double score) {
        this.score = score;
    }

    public String getScore() {
        return score + "";
    }
}
