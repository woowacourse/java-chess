package chess.controller.resposne;

public class StatusResponse {

    private final double whiteScore;
    private final double blackScore;

    public StatusResponse(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
