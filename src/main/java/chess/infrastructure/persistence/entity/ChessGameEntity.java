package chess.infrastructure.persistence.entity;

public class ChessGameEntity {

    private Long id;
    private final String turn;
    private final String winner;

    public ChessGameEntity(final Long id, final String turn, final String winner) {
        this.id = id;
        this.turn = turn;
        this.winner = winner;
    }

    public Long id() {
        return id;
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
