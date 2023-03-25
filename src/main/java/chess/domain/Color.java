package chess.domain;

public enum Color {
    BLACK("검정색"),
    WHITE("흰색"),
    EMPTY("없음");

    private final String player;

    Color(final String color) {
        this.player = color;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public String getPlayer() {
        return player;
    }
}
