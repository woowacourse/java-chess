package chess.dto;

public class StatusDto {

    private final String whiteScore;
    private final String blackScore;
    private final String whiteResult;
    private final String blackResult;

    public StatusDto(double whiteScore, double blackScore, String whiteResult, String blackResult) {
        this.whiteScore = Double.toString(whiteScore);
        this.blackScore = Double.toString(blackScore);
        this.whiteResult = whiteResult;
        this.blackResult = blackResult;
    }
}
