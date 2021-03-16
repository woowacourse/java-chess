package chess.domain.piece;

public enum PieceType {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k");

    private String type;

    PieceType(String type) {
        this.type = type;
    }

    public String toBlack() {
        return type.toUpperCase();
    }

    public String getType() {
        return type;
    }
}
