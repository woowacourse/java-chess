package chess.dto;

public class ScoreDTO {
    private final float whiteScore;
    private final float blackScore;

    public ScoreDTO(float whiteScore, float blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }
}
