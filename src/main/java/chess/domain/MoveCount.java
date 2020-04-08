package chess.domain;

public class MoveCount {

    private int moveCount;

    MoveCount() {
        this.moveCount = 0;
    }

    void setMoveCount() {
        this.moveCount = this.moveCount + 1;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
