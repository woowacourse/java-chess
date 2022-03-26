package chess.domain.piece;

public enum Team {

    WHITE,
    BLACK;

    public String convert(String value) {
        if (this == BLACK) {
            return value.toUpperCase();
        }
        return value.toLowerCase();
    }

    public Team change() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
