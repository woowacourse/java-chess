package chess.dto;

public class GameResultResponse {

    private final double whiteScore;
    private final double blackScore;
    private final String winCamp;

    public GameResultResponse(final double whiteScore, final double blackScore, final String winCamp) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
        this.winCamp = winCamp;
    }

}
