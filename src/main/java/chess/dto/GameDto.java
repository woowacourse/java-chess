package chess.dto;

public class GameDto {

    private final String turn;
    private final boolean isRunning;

    private GameDto(final String turn, final boolean isRunning) {
        this.turn = turn;
        this.isRunning = isRunning;
    }

    public static GameDto create() {
        return new GameDto("White", true);
    }

    public static GameDto from(final String turn) {
        return new GameDto(turn, true);
    }

    public String getTurn() {
        return turn;
    }

    public boolean getIsRunning() {
        return isRunning;
    }
}
