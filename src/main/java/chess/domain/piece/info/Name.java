package chess.domain.piece.info;

public enum Name {
    BISHOP("B"),
    KING("K"),
    KNIGHT("N"),
    PAWN("P"),
    QUEEN("Q"),
    ROOK("R"),
    NONE(".");

    private final String name;

    Name(String name) {
        this.name = name;
    }

    public String nameByColor(Color color) {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }
}
