package chess.dao.dto;

public class ChessGameDto {

    private final Long id;
    private final String state;
    private final String turn;

    public ChessGameDto(final Long id, final String state, final String turn) {
        this.id = id;
        this.state = state;
        this.turn = turn;
    }

    public static ChessGameDto of(final Long id, final String state, final String turn) {
        return new ChessGameDto(id, state, turn);
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getTurn() {
        return turn;
    }
}
