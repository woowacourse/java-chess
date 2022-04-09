package chess.dto;

import java.util.List;

public class ScoreDto {
    private double whiteScore;
    private double blackScore;

    public ScoreDto(List<Double> score) {
        this.whiteScore = score.get(0);
        this.blackScore = score.get(1);
    }
}
