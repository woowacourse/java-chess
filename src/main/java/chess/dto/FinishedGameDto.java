package chess.dto;

public class FinishedGameDto {

    private final String winner;

    public FinishedGameDto(final String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
