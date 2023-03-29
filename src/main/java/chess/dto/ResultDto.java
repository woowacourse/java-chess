package chess.dto;

public class ResultDto {

    private final String winner;

    public ResultDto(final String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
