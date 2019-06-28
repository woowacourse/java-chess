package chess.domain.pieces;

public enum Color {
    WHITE("w"),
    BLACK("b"),
    NONE("n");

    private String initial;

    Color(String initial) {
        this.initial = initial;
    }

    public String getInitial() {
        return initial;
    }
}
