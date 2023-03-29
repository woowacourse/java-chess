package chess.dto;

public class GameDto {

    private final int id;
    private final String turn;
    private final boolean isRunning;

    private GameDto(final int id, final String turn, final boolean isRunning) {
        this.id = id;
        this.turn = turn;
        this.isRunning = isRunning;
    }

    public static GameDto create() {
        return new GameDto(0, "White", true);
    }

    public static GameDto from(final boolean isRunning) {
        return new GameDto(0, null, isRunning);
    }

    public static GameDto from(final int id) {
        return new GameDto(id, null, true);
    }

    public static GameDto of(final int id, final String turn) {
        return new GameDto(id, turn, true);
    }

    public static GameDto of(final int id, final boolean isRunning) {
        return new GameDto(id, null, isRunning);
    }

    public String getTurn() {
        return turn;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public int getId() {
        return id;
    }
}
