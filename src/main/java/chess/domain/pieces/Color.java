package chess.domain.pieces;

public enum Color {

    BLACK("검은말"),
    WHITE("흰말"),
    ;

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public static Color opposite(final Color color) {
        if (color.isWhite()) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public String value() {
        return value;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
