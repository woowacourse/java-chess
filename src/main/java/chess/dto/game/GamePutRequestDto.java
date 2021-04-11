package chess.dto.game;

public class GamePutRequestDto {

    private final long id;
    private final String turn;
    private final boolean isFinished;

    public GamePutRequestDto(final long id, final String turn, final boolean isFinished) {
        this.id = id;
        this.turn = turn;
        this.isFinished = isFinished;
    }

    public long getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
