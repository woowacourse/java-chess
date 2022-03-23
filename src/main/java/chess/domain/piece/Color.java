package chess.domain.piece;

public enum Color {
    BLACK("B"),
    WHITE("W"),
    NONE("N");

    private final String value;

    Color(String value) {
        this.value = value;
    }
}
