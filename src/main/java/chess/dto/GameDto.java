package chess.dto;

public class GameDto {

    private final String turn;
    private final boolean isRunning;

    public GameDto(final String turn, final boolean isRunning) {
        this.turn = turn;
        this.isRunning = isRunning;
    }

    public String getTurn() {
        return turn;
    }

    public boolean getIsRunning() {
        return isRunning;
    }
}
