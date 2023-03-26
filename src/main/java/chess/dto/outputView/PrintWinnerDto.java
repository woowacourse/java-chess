package chess.dto.outputView;

public final class PrintWinnerDto {

    private final String winnerTeam;

    public PrintWinnerDto(final String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }
}
