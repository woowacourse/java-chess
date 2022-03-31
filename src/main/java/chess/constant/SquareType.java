package chess.constant;

public enum SquareType {
    FRIENDLY("아군"),
    ENEMY("적군"),
    EMPTY("비어 있음");

    private final String description;

    SquareType(String description) {
        this.description = description;
    }
}
