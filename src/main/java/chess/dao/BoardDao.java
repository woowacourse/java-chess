package chess.dao;

public class BoardDao {

    private final Long id;
    private final String chessBoardPosition;

    public BoardDao(final Long id, final String chessBoardPosition) {
        this.id = id;
        this.chessBoardPosition = chessBoardPosition;
    }

    public Long id() {
        return id;
    }

    public String chessBoardPosition() {
        return chessBoardPosition;
    }
}
