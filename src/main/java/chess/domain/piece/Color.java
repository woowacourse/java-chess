package chess.domain.piece;

public enum Color {

    WHITE("white"),
    BLACK("black");

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public static Color find(String name) {
        if ("white".equals(name)) {
            return WHITE;
        }
        return BLACK;
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
