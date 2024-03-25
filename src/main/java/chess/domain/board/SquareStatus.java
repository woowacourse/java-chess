package chess.domain.board;

public enum SquareStatus {
    EMPTY,
    ENEMY,
    PEER;

    public boolean isPeer() {
        return this == PEER;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }
}
