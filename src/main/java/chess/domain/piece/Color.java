package chess.domain.piece;

public enum Color {

    WHITE("백"),
    BLACK("흑");

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public Color reversed() {
        if(WHITE.equals(this)) {
            return BLACK;
        }
        return WHITE;
    }

    public String getName() {
        return name;
    }
}
