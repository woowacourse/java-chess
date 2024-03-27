package chessgame.domain.piece.kind;

public enum PieceStatus {
    KING("k"),
    KNIGHT("n"),
    PAWN("p"),
    QUEEN("q"),
    ROOK("r"),
    BISHOP("b");

    private final String value;

    PieceStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
