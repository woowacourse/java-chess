package chess.domain.piece;

public enum Color {

    WHITE("백색"),
    BLACK("흑색"),
    NONE("없음");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public Color nextTurn() {
        if (this.isWhite()) {
            return BLACK;
        }
        return WHITE;
    }

    public String getName() {
        return name;
    }
}


