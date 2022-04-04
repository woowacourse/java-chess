package chess.controller.dto;

public class StatusDTO {
    private final float whiteScore;
    private final float blackScore;

    public StatusDTO(float whiteScore, float blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public float getWhiteScore() {
        return whiteScore;
    }

    public float getBlackScore() {
        return blackScore;
    }
}
