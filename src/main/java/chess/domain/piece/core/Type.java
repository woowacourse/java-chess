package chess.domain.piece.core;

public enum Type {
    KING("k"),
    QUEEN("q"),
    BISHOP("b"),
    KNIGHT("N"),
    ROOK("r"),
    PAWN("p");

    String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
