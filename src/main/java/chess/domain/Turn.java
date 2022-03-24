package chess.domain;

public class Turn {
    private int count;

    public Turn() {
        this.count = 1;
    }

    public boolean isBlackTurn() {
        return count % 2 == 0;
    }

    public void countTurn() {
        count++;
    }
}
