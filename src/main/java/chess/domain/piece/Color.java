package chess.domain.piece;

public enum Color {

    BLACK("black"),
    WHITE("white");

    private final String name;

    Color(String name) {
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
