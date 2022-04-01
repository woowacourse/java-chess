package chess.constant;

public enum TargetType {
    FRIENDLY("아군"),
    ENEMY("적군"),
    EMPTY("비어 있음");

    private final String description;

    TargetType(String description) {
        this.description = description;
    }

    public boolean isEnemy() {
        return this == ENEMY;
    }
}
