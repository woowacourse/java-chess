package chess.domain;

public enum Color {

    BLACK("검은말"),
    WHITE("흰말");

    private final String text;

    Color(String text) {
        this.text = text;
    }

    public static Color opposite(Color turn) {
        if (turn.isWhite()) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public String text() {
        return text;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

}
