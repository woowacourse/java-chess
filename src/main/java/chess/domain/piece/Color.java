package chess.domain.piece;

public enum Color {
    WHITE("백"),
    BLACK("흑");

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
