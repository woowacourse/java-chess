package chess.domain.piece;

public enum PieceType {
    BISHOP("B"),
    ROOK("R"),
    QUEEN("Q"),
    KNIGHT("N"),
    KING("K"),
    PAWN("P");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }

    public String formatName(Color color) {
        return color.formatName(name);
    }
}
