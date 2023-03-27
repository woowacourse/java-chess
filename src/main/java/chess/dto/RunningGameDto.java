package chess.dto;

public class RunningGameDto {

    private final String turn;

    public RunningGameDto(final String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }
}
