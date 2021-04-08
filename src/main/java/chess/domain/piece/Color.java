package chess.domain.piece;

public enum Color {

    WHITE("white"),
    BLACK("black"),
    NOTHING("nothing");

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public static Color find(String name) {
        if (WHITE.name.equals(name)) {
            return WHITE;
        }
        if (BLACK.name.equals(name)) {
            return BLACK;
        }
        return NOTHING;
    }

    public Color reversed() {
        if (WHITE.equals(this)) {
            return BLACK;
        }
        if (NOTHING.equals(this)) {
            return this;
        }
        return WHITE;
    }

    public String getName() {
        return name;
    }
}
