package chess.dto;

public class ChessGameDTO {
    private TeamDTO blackTeam;
    private TeamDTO whiteTeam;
    private boolean isRunning;

    public ChessGameDTO(final TeamDTO blackTeam, final TeamDTO whiteTeam, final boolean isRunning) {
        this.blackTeam = blackTeam;
        this.whiteTeam = whiteTeam;
        this.isRunning = isRunning;
    }

    public TeamDTO getBlackTeam() {
        return blackTeam;
    }

    public void setBlackTeam(final TeamDTO blackTeam) {
        this.blackTeam = blackTeam;
    }

    public TeamDTO getWhiteTeam() {
        return whiteTeam;
    }

    public void setWhiteTeam(final TeamDTO whiteTeam) {
        this.whiteTeam = whiteTeam;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(final boolean running) {
        isRunning = running;
    }
}
