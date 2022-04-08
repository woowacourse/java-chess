package chess.piece;

public enum Color {

    BLACK("BLACK"),
    WHITE("WHITE"),
    NONE("NONE");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
