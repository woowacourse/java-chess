package chess.domain.piece;

public enum Type {

    PAWN("P"),
    QUEEN("Q"),
    KING("K"),
    BISHOP("B"),
    KNIGHT("N"),
    ROOK("R"),
    BLANK(".");

    String name;

    Type(String name) {
        this.name = name;
    }

    public String nameByColor(Color color) {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }
}
