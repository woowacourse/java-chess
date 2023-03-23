package chess.dao;

public class BoardSearchDao {

    private final Long id;
    private final String position;
    private final String turn;

    public BoardSearchDao(final Long id, final String position, final String turn) {
        this.id = id;
        this.position = position;
        this.turn = turn;
    }

    public Long id() {
        return id;
    }

    public String position() {
        return position;
    }

    public String turn() {
        return turn;
    }
}
