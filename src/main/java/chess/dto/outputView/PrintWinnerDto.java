package chess.dto.outputView;

public final class PrintWinnerDto {

    private final String winnerTeam;

    public PrintWinnerDto(final String winnerTeam) {
        this.winnerTeam = String.format("왕이 죽었습니다. 승자는 %s팀 입니다.", winnerTeam);
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }
}
