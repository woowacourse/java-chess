package chess.domain.piece;

public enum Color {

    WHITE("백팀"),
    BLACK("흑팀");

    private final String value;

    Color(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
