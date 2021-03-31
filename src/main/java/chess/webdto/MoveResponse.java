package chess.webdto;

public class MoveResponse {
    private String start;
    private String destination;
    private double whiteTeamScore;
    private double blackTeamScore;
    private String currentTurnTeam;
    private boolean isPlaying;

    public MoveResponse(String start, String destination, double whiteTeamScore, double blackTeamScore, String currentTurnTeam, boolean isPlaying) {
        this.start = start;
        this.destination = destination;
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
        this.currentTurnTeam = currentTurnTeam;
        this.isPlaying = isPlaying;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }

    public double getBlackTeamScore() {
        return blackTeamScore;
    }

    public String getCurrentTurnTeam() {
        return currentTurnTeam;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }
}
