package chess.dao;

public class BoardRegisterDao {

    private final String position;
    private final String turn;

    public BoardRegisterDao(final String position, final String turn) {
        this.position = position;
        this.turn = turn;
    }

    public String position() {
        return position;
    }

    public String turn() {
        return turn;
    }
}
