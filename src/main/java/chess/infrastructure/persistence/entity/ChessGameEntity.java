package chess.infrastructure.persistence.entity;

public class ChessGameEntity {

    private Long id;
    private final String state;
    private final String turn;
    private final String winner;

    public ChessGameEntity(final Long id, final String state, final String turn, final String winner) {
        this.id = id;
        this.state = state;
        this.turn = turn;
        this.winner = winner;
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

    public String winner() {
        return winner;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
