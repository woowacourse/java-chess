package chess.domain.piece;

public enum Color {
    BLACK("블랙"),
    WHITE("화이트"),
    NONE("");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public Color switchColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getName(){
        return name;
    }
}
