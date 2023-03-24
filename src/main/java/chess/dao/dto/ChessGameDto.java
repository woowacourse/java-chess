package chess.dao.dto;

public class ChessGameDto {

    private final Long id;
    private final String turn;
    private final String state;

    public ChessGameDto(final Long id, final String turn, final String state) {
        this.id = id;
        this.turn = turn;
        this.state = state;
    }

    public static ChessGameDto of(final Long id, final String turn, final String state) {
        return new ChessGameDto(id, turn, state);
    }

    public Long getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }

    public String getState() {
        return state;
    }
}
