package chess.constant;

public enum MoveType {
    FRIENDLY("아군"),
    ENEMY("적군"),
    EMPTY("비어 있음");

    private final String description;

    MoveType(String description) {
        this.description = description;
    }
}
