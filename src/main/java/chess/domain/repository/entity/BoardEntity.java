package chess.domain.repository.entity;

public class BoardEntity {
    private final String turn;

    public BoardEntity(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }
}
