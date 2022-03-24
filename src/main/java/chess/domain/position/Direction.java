package chess.domain.position;

public enum Direction {

    RIGHT,
    LEFT,
    TOP,
    BOTTOM,
    TOP_RIGHT,
    TOP_LEFT,
    BOTTOM_RIGHT,
    BOTTOM_LEFT
    ;

    public static Direction of(Position source, Position target) {
        return RIGHT;
    }
}
