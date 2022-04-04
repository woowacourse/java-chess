package chess.dto;

public class GameDto {

    private final Integer id;
    private final String state;
    private final String turn;

    public GameDto(final String state, final String turn) {
        this(null, state, turn);
    }

    public GameDto(final Integer id, final String state, final String turn) {
        this.id = id;
        this.state = state;
        this.turn = turn;
    }

    public Integer getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getTurn() {
        return turn;
    }
}
