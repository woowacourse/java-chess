package chess.domain;

public enum Type {
    KING("", (start, end) -> true),
    QUEEN("", (start, end) -> true),
    ROOK("", (start, end) -> true),
    KNIGHT("", (start, end) -> true),
    BISHOP("", (start, end) -> true),
    PAWN("", (start, end) -> true);

    private final String name;
    private final MovingStrategy movingStrategy;

    Type(final String name, final MovingStrategy movingStrategy) {
        this.name = name;
        this.movingStrategy = movingStrategy;
    }
}
