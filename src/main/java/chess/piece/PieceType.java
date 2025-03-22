package chess.piece;

public enum PieceType {
    BISHOP("B"),
    KNIGHT("K"),
    PAWN("P"),
    QUEEN("Q"),
    KING("왕"),
    ROOK("R");

    private final String name;

    PieceType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
