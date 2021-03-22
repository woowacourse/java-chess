package chess.controller.dto.response;

public class ScoresResponseDTO {
    private final double blackTeamScore;
    private final double whiteTeamScore;

    public ScoresResponseDTO(double blackTeamScore, double whiteTeamScore) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
    }

    public double getBlackTeamScore() {
        return blackTeamScore;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }
}
