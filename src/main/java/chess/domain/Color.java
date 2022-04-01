package chess.domain;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    NONE("none");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public String getValue() {
        return value;
    }
}
