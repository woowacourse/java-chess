package chess.controller.dto;

public class WinnerDto {
    private final String winner;

    public WinnerDto(final String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
