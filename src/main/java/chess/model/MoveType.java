package chess.model;

public enum MoveType {
    FRIENDLY, ENEMY, EMPTY;

    public boolean isFriendly() {
        return this == FRIENDLY;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }
}
