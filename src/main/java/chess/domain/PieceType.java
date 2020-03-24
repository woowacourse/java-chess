package chess.domain;

public enum PieceType {
    PAWN("p"),
    KNIGHT("n"),
    ROOK("r"),
    BISHOP("b"),
    KING("k"),
    QUEEN("q"),
    BLANK(".");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
