package chess.domain;

public enum Color {

    BLACK("검은말"), 
    WHITE("흰말");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
