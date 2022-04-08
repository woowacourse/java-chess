package chess.web.service.dto;

public class ScoreDto {
    private final double blackTeamScore;
    private final double whiteTeamScore;

    public ScoreDto(double blackTeamScore, double whiteTeamScore) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }

    public double getBlackTeamScore() {
        return blackTeamScore;
    }

}
