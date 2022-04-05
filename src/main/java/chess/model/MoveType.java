package chess.model;

public enum MoveType {
    FRIENDLY, ENEMY, EMPTY;

    public boolean isFriendly() {
        return this == FRIENDLY;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }

    public static MoveType of(boolean isTargetEmpty, boolean isTargetFriendly) {
        if (isTargetEmpty) {
            return MoveType.EMPTY;
        }
        if (isTargetFriendly) {
            return MoveType.FRIENDLY;
        }
        return MoveType.ENEMY;
    }
}
