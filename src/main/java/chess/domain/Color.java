package chess.domain;

public enum Color {

    BLACK("black"),
    WHITE("white"),
    NONE("none")
    ;

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public String getName() {
        return name;
    }
}
