package chess;

public enum PieceType {
    EMPTY("."),
    PAWN("P"),
    KNIGHT("N"),
    ROOK("R"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
