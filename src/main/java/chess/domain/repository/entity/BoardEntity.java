package chess.domain.repository.entity;

public class BoardEntity {
    private final String turn;
    private final String state;

    public BoardEntity(String turn, String state) {
        this.turn = turn;
        this.state = state;
    }

    public String getTurn() {
        return turn;
    }

    public String getState() {
        return state;
    }
}
