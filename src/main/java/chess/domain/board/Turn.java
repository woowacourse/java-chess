package chess.domain.board;

public class Turn {

    private static final int BLACK_TURN = 0;
    private static final int WHITE_TURN = 1;

    private int count;

    public Turn() {
        this.count = 1;
    }

    public void update() {
        count++;
    }

    public boolean isBlackTurn() {
        return count % 2 == BLACK_TURN;
    }

    public boolean isWhiteTurn() {
        return count % 2 == WHITE_TURN;
    }
}
