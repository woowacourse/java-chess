package chess.web.service.dto;

public class ScoreDto {
    private final double blackTeam;
    private final double whiteTeam;

    public ScoreDto(double blackTeam, double whiteTeam) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
    }

    public double getWhiteTeam() {
        return whiteTeam;
    }

    public double getBlackTeam() {
        return blackTeam;
    }

}
