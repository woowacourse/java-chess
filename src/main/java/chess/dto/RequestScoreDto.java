package chess.dto;

public class RequestScoreDto {
    private final String whiteScore;
    private final String blackScore;

    public RequestScoreDto(String whiteScore, String blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public String getWhiteScore() {
        return whiteScore;
    }

    public String getBlackScore() {
        return blackScore;
    }
}
