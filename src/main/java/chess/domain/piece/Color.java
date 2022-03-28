package chess.domain.piece;

public enum Color {

    BLACK("검은색"),
    WHITE("흰색"),
    ;

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public Color reversed() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getName() {
        return name;
    }
}
