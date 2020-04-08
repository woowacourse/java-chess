package chess.domain;

public class MoveCount {

    private int moveCount;

    MoveCount() {
        this.moveCount = 0;
    }

    void setMoveCount() {
        this.moveCount++;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
