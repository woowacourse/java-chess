package chess.domain.piece;

public enum PieceColor {
    WHITE("백"),
    BLACK("흑"),
    NOTHING("무");

    private final String color;

    PieceColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public PieceColor reversed() {
        if (this.equals(NOTHING)) {
            return this;
        }
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
