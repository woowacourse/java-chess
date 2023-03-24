package chess.infrastructure.persistence.entity;

public class ChessGameEntity {

    private Long id;
    private final String state;
    private final String turn;

    public ChessGameEntity(final Long id, final String state, final String turn) {
        this.id = id;
        this.state = state;
        this.turn = turn;
    }

    public Long id() {
        return id;
    }

    public String state() {
        return state;
    }

    public String turn() {
        return turn;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
