package chess.domain.piece;

public enum Type {

    KING("king"),
    QUEEN("queen"),
    BISHOP("bishop"),
    KNIGHT("knight"),
    ROOK("rook"),
    PAWN("pawn"),
    EMPTY("empty")
    ;

    private final String name;

    Type(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
