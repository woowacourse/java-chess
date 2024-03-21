package chess.domain.board;

public class Turn {
    private int count;

    public Turn() {
        this.count = 1;
    }

    public void update() {
        count++;
    }

    public boolean isBlackTurn() {
        return count % 2 == 0;
    }

    public boolean isWhiteTurn() {
        return count % 2 == 1;
    }
}
