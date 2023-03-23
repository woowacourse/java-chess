package chess.domain.piece;

public enum PieceType {
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    PAWN("p"),
    EMPTY(".");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
