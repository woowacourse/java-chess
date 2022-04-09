package chess.domain.dao;

public class FakeBoard {

    private final int turn;

    public FakeBoard(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }
}
