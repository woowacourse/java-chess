package chess.domain.piece;

public enum Color {
    Black("B"),
    White("W")
    ;

    private final String value;

    Color(String value) {
        this.value = value;
    }
}
