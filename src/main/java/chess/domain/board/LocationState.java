package chess.domain.board;

public enum LocationState {
    EMPTY, ALLY, ENEMY;

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }
}
