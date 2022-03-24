package chess.domain.piece;

public enum Color {

    BLACK("흑"),
    WHITE("백"),
    NONE("none");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public Color oppositeColor() {
        if (this == NONE) {
            throw new IllegalStateException("[ERROR] 상대팀이 없습니다.");
        }
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
