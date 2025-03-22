package chess.piece;

public enum PieceMoveType {

    BISHOP("B"), KING("K"), KNIGHT("N"),
    PAWN("P"), QUEEN("Q"), ROOK("R");

    private final String value;

    PieceMoveType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
