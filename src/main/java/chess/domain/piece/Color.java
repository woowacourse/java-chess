package chess.domain.piece;

public enum Color {
    BLACK("Black"),
    WHITE("White");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public Color changeColor(Color color) {
        if (color.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }

    public static Color of(String color) {
        if (color.equals(BLACK.name)) {
            return BLACK;
        }
        if (color.equals(WHITE.name)) {
            return WHITE;
        }
        throw new UnsupportedOperationException("존재하지 않는 팀 색입니다.");
    }

    public String getName() {
        return name;
    }

}
