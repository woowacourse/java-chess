package chess.domain.piece;

public enum Color {

    BLACK("black"),
    WHITE("white"),
    NONE("none"),
    ;

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public Color getOpposite() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getName() {
        return name;
    }
}
