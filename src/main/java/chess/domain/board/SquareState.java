package chess.domain.board;

public enum SquareState {
    EMPTY, ALLY, ENEMY;

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }
}
